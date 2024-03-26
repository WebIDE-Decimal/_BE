package com.goormpj.decimal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "https://localhost:5173", "https://43.203.98.60:8443", "https://groomcosmos.site")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
