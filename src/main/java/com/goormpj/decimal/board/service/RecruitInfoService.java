package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.entity.RecruitInfo;
import com.goormpj.decimal.board.entity.RecruitPost;

import java.util.List;

public interface RecruitInfoService {
    List<RecruitInfo> findAll();
    RecruitInfo findById(Long id);
    RecruitInfo save(RecruitInfo recruitInfo);
    void softDelete(Long id);           // soft delete
}