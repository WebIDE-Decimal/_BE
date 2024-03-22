package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.dto.RecruitInfoDTO;
import com.goormpj.decimal.board.entity.RecruitInfo;
import com.goormpj.decimal.board.entity.State;
import com.goormpj.decimal.board.mapper.RecruitInfoMapper;
import com.goormpj.decimal.board.repository.RecruitInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RecruitInfoServiceImpl implements RecruitInfoService {
    private final RecruitInfoRepository recruitInfoRepository;

    public RecruitInfoServiceImpl(RecruitInfoRepository recruitInfoRepository) {
        this.recruitInfoRepository = recruitInfoRepository;
    }

    // 게시글 생성
    @Override
    @Transactional
    public RecruitInfoDTO createRecruitInfo(RecruitInfoDTO recruitInfoDTO) {
        RecruitInfo recruitInfo = RecruitInfoMapper.toEntity(recruitInfoDTO);
        recruitInfo = recruitInfoRepository.save(recruitInfo);
        return RecruitInfoMapper.toDto(recruitInfo);
    }


    // 게시글 조회
    @Override
    @Transactional(readOnly = true)
    public RecruitInfoDTO getRecruitInfoById(Long id) {
        RecruitInfo recruitInfo = recruitInfoRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("RecruitInfo not found for id: " + id));
        return RecruitInfoMapper.toDto(recruitInfo);
    }

    // 게시글 논리삭제
    @Override
    @Transactional
    public void deleteRecruitInfo(Long id) {
        RecruitInfo recruitInfo = recruitInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RecruitInfo not found for id: " + id));
        recruitInfo.setIsDeleted(true);
        recruitInfoRepository.save(recruitInfo);
    }

    // 게시글 수정
    @Override
    @Transactional
    public RecruitInfoDTO updateRecruitInfo(Long id, RecruitInfoDTO recruitInfoDTO) {
        RecruitInfo recruitInfo = recruitInfoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("RecruitInfo not found for id: " + id));
        recruitInfo.setMotivation(recruitInfoDTO.getMotivation());
        RecruitInfo updatedRecruitInfo = recruitInfoRepository.save(recruitInfo);
        return RecruitInfoMapper.toDto(updatedRecruitInfo);
    }

    // 모집 수락 혹은 거절
    @Override
    @Transactional
    public void respondToRecruit(Long id, boolean accept) {
        RecruitInfo recruitInfo = recruitInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RecruitInfo not found for id: " + id));

        recruitInfo.setState(accept ? State.APPROVE : State.DISAPPROVE);
        recruitInfoRepository.save(recruitInfo);
    }
}
