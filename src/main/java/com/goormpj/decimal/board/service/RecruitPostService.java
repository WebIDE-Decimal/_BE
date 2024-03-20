package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.entity.RecruitPost;
import java.util.List;
import java.util.Optional;

public interface RecruitPostService {
    List<RecruitPost> findAllNotDeleted(); // 소프트 삭제되지 않은 모든 게시글 조회
    Optional<RecruitPost> findByIdNotDeleted(Long id); // 소프트 삭제되지 않은 특정 ID의 게시글 조회
    RecruitPost save(RecruitPost recruitPost); // 게시글 저장
    void softDelete(Long id); // soft delete 추가

}


