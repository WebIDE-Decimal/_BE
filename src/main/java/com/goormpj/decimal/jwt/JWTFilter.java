package com.goormpj.decimal.jwt;

import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.dto.CustomUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 access_token 꺼내기
        String accessToken = request.getHeader("access_token");

        // 토큰이 없다면 다음 필터로 넘김
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtProvider.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            PrintWriter writer = response.getWriter();
            writer.print("access_token expired");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401 error
            return;
        }

        // 토큰이 access_token 인지 확인 (발급시 페이로드에 명시)
        String category = jwtProvider.getCategory(accessToken);
        if (!category.equals("access_token")) {
            PrintWriter writer = response.getWriter();
            writer.print("invalid access_token");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401 error
            return;
        }

        String memberId = jwtProvider.getMemberId(accessToken);
        Member member = new Member(Long.parseLong(memberId));
        log.info("JWTFilter class - member.getId() {}", member.getId());

        CustomUserDetails customUserDetails = new CustomUserDetails(member);
        log.info("JWTFilter class - customUserDetails.getUsername() {}", customUserDetails.getUsername());
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);

    }
}
