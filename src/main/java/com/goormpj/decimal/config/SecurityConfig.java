package com.goormpj.decimal.config;

import com.goormpj.decimal.jwt.CustomLogoutFilter;
import com.goormpj.decimal.jwt.JWTFilter;
import com.goormpj.decimal.jwt.JWTProvider;
import com.goormpj.decimal.jwt.LoginFilter;
import com.goormpj.decimal.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity //추가
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    private final JWTProvider jwtProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(basic->basic.disable())
                .formLogin(form -> form.disable())
                .csrf(csrf->csrf.disable())
                .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://43.203.98.60:8080","https://groomcosmos.site"));
                            config.setAllowedMethods(Arrays.asList("*"));
                            config.setAllowCredentials(true);
                            config.setAllowedHeaders(Arrays.asList("*"));
                            config.setMaxAge(3600L);

                            config.setExposedHeaders(Arrays.asList("Set-Cookie"));
                            config.setExposedHeaders(Arrays.asList("access_token"));
                            return config;
                        }))
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/**").permitAll() // 모든 경로 허용
                        .requestMatchers("/api/users/signup", "/", "/api/users/login","/login").permitAll()
                        .requestMatchers("/reissue").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new JWTFilter(jwtProvider), LoginFilter.class)
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration) , jwtProvider, refreshTokenRepository), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CustomLogoutFilter(jwtProvider, refreshTokenRepository), LogoutFilter.class)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }


}
