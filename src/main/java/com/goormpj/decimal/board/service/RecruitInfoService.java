package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.dto.RecruitInfoDTO;

import java.util.List;

public interface RecruitInfoService {
    RecruitInfoDTO createRecruitInfo(RecruitInfoDTO recruitInfoDTO);
    RecruitInfoDTO getRecruitInfoById(Long id);
    void deleteRecruitInfo(Long id);
    RecruitInfoDTO updateRecruitInfo(Long id, RecruitInfoDTO recruitInfoDTO);
    void respondToRecruit(Long id, boolean accept);
}