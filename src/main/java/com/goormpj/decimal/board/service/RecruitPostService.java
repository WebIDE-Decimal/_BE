package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.dto.RecruitPostRequestDTO;
import com.goormpj.decimal.board.dto.RecruitPostResponseDTO;
import com.goormpj.decimal.board.entity.RecruitPost;
import com.goormpj.decimal.user.dto.CustomUserDetails;

import java.util.List;

public interface RecruitPostService {
    List<RecruitPost> findAllNotDeleted();

    List<RecruitPostResponseDTO> getMyRecruitPosts(CustomUserDetails customUserDetails);

    RecruitPost findByIdNotDeleted(Long id);
    RecruitPost createRecruitPost(RecruitPostRequestDTO requestDTO, CustomUserDetails customUserDetails);
    RecruitPost updateRecruitPost(Long id, RecruitPost recruitPost);
    void softDelete(Long id);
    void updateRecruitmentState(Long postId, Boolean newState); // 메서드 추가

}