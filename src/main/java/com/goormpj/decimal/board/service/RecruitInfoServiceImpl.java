package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.entity.RecruitInfo;
import com.goormpj.decimal.board.repository.RecruitInfoRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruitInfoServiceImpl implements RecruitInfoService {
    private final RecruitInfoRepository recruitInfoRepository;

    @Autowired
    public RecruitInfoServiceImpl(RecruitInfoRepository recruitInfoRepository) {
        this.recruitInfoRepository = recruitInfoRepository;
    }

    @Override
    public List<RecruitInfo> findAll() {
        return recruitInfoRepository.findAll();
    }

    @Override
    public RecruitInfo findById(Long id) {
        Optional<RecruitInfo> result = recruitInfoRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public RecruitInfo save(RecruitInfo recruitInfo) {
        return recruitInfoRepository.save(recruitInfo);
    }

    @Override
    public void softDelete(Long id) {
        recruitInfoRepository.findById(id).ifPresent(recruitInfo -> {
            recruitInfo.setIsDeleted(true);
            recruitInfoRepository.save(recruitInfo);
        });
    }
}
