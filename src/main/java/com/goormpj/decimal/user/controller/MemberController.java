package com.goormpj.decimal.user.controller;

import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.dto.CustomUserDetails;
import com.goormpj.decimal.user.dto.LoginResponseDTO;
import com.goormpj.decimal.user.dto.SignUpRequestDTO;
import com.goormpj.decimal.user.mapper.MemberMapper;
import com.goormpj.decimal.user.service.MemberService;
import com.goormpj.decimal.user.util.AuthUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    private final AuthUtils authUtils;

    //로그인한 회원 정보 가져오기 예시
    @GetMapping("/memberProfile")
    public ResponseEntity<Member> getMemberProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Optional<Member> loggedInMember = authUtils.getLoggedInMember(customUserDetails);
        return ResponseEntity.ok().body(loggedInMember.orElseThrow());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Validation 에러가 있을 경우 처리
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach((error) -> {
                String errorMessage = error.getDefaultMessage();
                errors.append(errorMessage).append("\n");
            });
            return ResponseEntity.badRequest().body(errors.toString());
        }

        //이메일 중복 확인
        if(memberService.existsByEmail(signUpRequestDTO.getEmail())){
            return ResponseEntity.badRequest().body("이메일 중복 오류");
        }

        // Validation 통과 시 회원가입 로직 수행
        memberService.save(signUpRequestDTO);

        return ResponseEntity.ok().body("회원가입 성공");
    }

}
