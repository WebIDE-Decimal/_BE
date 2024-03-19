package com.goormpj.decimal.user.controller;

import com.goormpj.decimal.jwt.JWTProvider;
import com.goormpj.decimal.user.domain.RefreshToken;
import com.goormpj.decimal.user.repository.RefreshTokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ReissueController {

    private final JWTProvider jwtProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        //get refresh_token from cookies
        String refreshToken = getRefreshToken(request);

        if (refreshToken == null) {
            return new ResponseEntity<>("refresh_token null", HttpStatus.BAD_REQUEST);
        }

        //expired check
        try {
            jwtProvider.isExpired(refreshToken);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("refresh_token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtProvider.getCategory(refreshToken);

        if (!category.equals("refresh_token")) {
            return new ResponseEntity<>("invalid refresh_token", HttpStatus.BAD_REQUEST);
        }

        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshTokenRepository.existsByRefreshToken(refreshToken);
        if (!isExist) {
            return new ResponseEntity<>("invalid refresh_token", HttpStatus.BAD_REQUEST);
        }

        String memberId = jwtProvider.getMemberId(refreshToken);
        String role = jwtProvider.getRole(refreshToken);

        String newAccessToken = jwtProvider.createJwt("access_token", memberId, role, 600000L);
        String newRefreshToken = jwtProvider.createJwt("refresh_token", memberId, role, 86400000L);

        //DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
        addRefreshToken(memberId, newRefreshToken, 86400000L);

        response.setHeader("access_token", newAccessToken);
        response.addCookie(createCookie("refresh_token", newRefreshToken));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String getRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh_token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true); //https
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    private void addRefreshToken(String memberId, String refresh, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);
        RefreshToken refreshToken = RefreshToken.builder()
                .memberId(memberId)
                .refreshToken(refresh)
                .expirationDate(date)
                .build();
        refreshTokenRepository.save(refreshToken);
    }

}
