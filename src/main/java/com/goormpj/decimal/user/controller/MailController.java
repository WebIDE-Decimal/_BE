package com.goormpj.decimal.user.controller;

import com.goormpj.decimal.user.dto.VerificationEmailRequest;
import com.goormpj.decimal.user.service.MailService;
import com.goormpj.decimal.user.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verify-email")
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;

    private final MemberService memberService;

    @PostMapping("/send")
    public ResponseEntity<String> sendVerificationEmail(@Valid @RequestBody VerificationEmailRequest verificationEmailRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Validation 에러가 있을 경우 처리
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach((error) -> {
                String errorMessage = error.getDefaultMessage();
                errors.append(errorMessage).append("\n");
            });
            return ResponseEntity.badRequest().body(errors.toString());
        }

        if(memberService.existsByEmail(verificationEmailRequest.getUserEmail())){
            return ResponseEntity.badRequest().body("이미 가입된 이메일 주소입니다.");
        }


        try {
            mailService.sendMail(verificationEmailRequest.getUserEmail());
            return ResponseEntity.ok("인증 메일 전송 완료");
        } catch (Exception e) {
            log.error("Failed to send verification email", e);
            return ResponseEntity.badRequest().body("error: 인증 메일 전송 실패");
        }
    }

    @GetMapping("/valid")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        Boolean isValid = mailService.verifyEmail(token);
        if (isValid) {
            return ResponseEntity.ok("이메일 인증 완료");
        } else {
            return ResponseEntity.badRequest().body("메일 인증 토큰이 유효하지 않습니다.");
        }
    }

}
