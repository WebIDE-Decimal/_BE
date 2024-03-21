package com.goormpj.decimal.user.repository;

import com.goormpj.decimal.user.domain.MailToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface MailTokenRepository extends JpaRepository<MailToken, Long> {

    MailToken findExpriyDateByToken(String token);

    MailToken findByEmail(String email);
}
