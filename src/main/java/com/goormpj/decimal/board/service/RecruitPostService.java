package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.entity.RecruitPost;

import java.util.List;

public interface RecruitPostService {
    List<RecruitPost> findAllNotDeleted();

    RecruitPost findByIdNotDeleted(Long id);
    RecruitPost createRecruitPost(RecruitPost recruitPost);
    RecruitPost updateRecruitPost(Long id, RecruitPost recruitPost);
    void softDelete(Long id);
}