package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.entity.RecruitPost;

import java.util.List;
import java.util.Optional;

public interface RecruitPostService {
    List<RecruitPost> findAll(); // 모든 게시글 찾기
    Optional<RecruitPost> findById(Long id); // ID로 게시글 찾기
    RecruitPost save(RecruitPost recruitPost); // 게시글 저장
    void delete(Long id); // 게시글 삭제
}
