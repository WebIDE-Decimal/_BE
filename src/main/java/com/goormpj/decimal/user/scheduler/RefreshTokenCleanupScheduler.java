package com.goormpj.decimal.user.scheduler;

import com.goormpj.decimal.user.domain.RefreshToken;
import com.goormpj.decimal.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RefreshTokenCleanupScheduler {

    private final RefreshTokenRepository refreshTokenRepository;

    // 매일 자정에 실행
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteExpiredRefreshTokens() {
        List<RefreshToken> expiredTokens = refreshTokenRepository.findByExpirationDateBefore(new Date());
        refreshTokenRepository.deleteAll(expiredTokens);
    }

}
