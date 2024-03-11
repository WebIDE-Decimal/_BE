package com.goormpj.decimal.user.controller;

import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.dto.LoginRequestDTO;
import com.goormpj.decimal.user.dto.LoginResponseDTO;
import com.goormpj.decimal.user.dto.SignUpRequestDTO;
import com.goormpj.decimal.user.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO, BindingResult bindingResult, HttpServletRequest request){
        //아이디 존재 확인
        if(!memberService.existsByEmail(loginRequestDTO.getEmail())){
            return ResponseEntity.badRequest().body("이메일 존재 X");
        }

        //비밀번호 일치 확인
        Boolean result = memberService.passwordCheck(loginRequestDTO);

        if(result){
            Member member = memberService.findByEmail(loginRequestDTO.getEmail());
            LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .build();

            HttpSession session = request.getSession(); //
            session.setAttribute("user", loginResponseDTO); //
            return ResponseEntity.ok().body("로그인 성공");
        }else{
            return ResponseEntity.badRequest().body("비밀번호 불일치");
        }

    }

    @GetMapping("/logout")
    public ResponseEntity<String> login(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); // 세션 무효화
            return ResponseEntity.ok().body("로그아웃 성공");
        } else {
            return ResponseEntity.ok().body("로그아웃 실패");
        }

    }

}
