package com.goormpj.decimal.user.service;

import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.dto.LoginRequestDTO;
import com.goormpj.decimal.user.dto.SignUpRequestDTO;

public interface MemberService {

    Member save(SignUpRequestDTO signUpRequestDTO); // 회원가입

    Boolean existsByEmail(String email);//이메일 존재 체크

    Member findByEmail(String email); //이메일로 회원 정보 조회

    Boolean passwordCheck(LoginRequestDTO loginRequestDTO); // 로그인

}