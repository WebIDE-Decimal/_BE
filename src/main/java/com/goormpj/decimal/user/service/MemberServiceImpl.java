package com.goormpj.decimal.user.service;

import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.dto.LoginRequestDTO;
import com.goormpj.decimal.user.dto.SignUpRequestDTO;
import com.goormpj.decimal.user.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //User save(SignUpRequestDTO signUpRequestDTO); 회원가입
    @Override
    public Member save(SignUpRequestDTO signUpRequestDTO) {
        Member member = Member.builder()
                .email(signUpRequestDTO.getEmail())
                .nickname(signUpRequestDTO.getNickname())
                .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                .build();
        return memberRepository.save(member);
    }

    //Boolean existsByEmail(String email); 이메일 존재 체크
    @Override
    public Boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    //Member findByEmail(String email); 이메일로 회원 정보 조회
    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    //void login(String email, String password); 로그인
    @Override
    public Boolean passwordCheck(LoginRequestDTO loginRequestDTO) {
        Member member = memberRepository.findByEmail(loginRequestDTO.getEmail());

        if(passwordEncoder.matches(loginRequestDTO.getPassword(),member.getPassword()))
            return true;
        else
            return false;

    }


}
