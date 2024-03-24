package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.entity.RecruitPost;
import java.util.List;

public interface RecruitPostService {
    List<RecruitPost> findAllNotDeleted();

    RecruitPost findByIdNotDeleted(Long id);
    public RecruitPost createRecruitPost(RecruitPost recruitPost, String username);
    RecruitPost updateRecruitPost(Long id, RecruitPost recruitPost);
    void softDelete(Long id);
    void updateRecruitmentState(Long postId, Boolean newState); // 메서드 추가

    //현재 모집 인원수 증가
    void incrementCurrentRecruited(Long postId);

    void applyToRecruitPost(Long postId, CustomUserDetails userDetails);



}
