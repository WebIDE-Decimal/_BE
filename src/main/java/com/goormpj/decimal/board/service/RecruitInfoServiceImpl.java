package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.dto.RecruitInfoDTO;
import com.goormpj.decimal.board.entity.RecruitInfo;
import com.goormpj.decimal.board.entity.RecruitPost;
import com.goormpj.decimal.board.entity.State;
import com.goormpj.decimal.board.mapper.RecruitInfoMapper;
import com.goormpj.decimal.board.repository.RecruitInfoRepository;
import com.goormpj.decimal.board.repository.RecruitPostRepository;
import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.dto.CustomUserDetails;
import com.goormpj.decimal.user.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecruitInfoServiceImpl implements RecruitInfoService {
    private final RecruitInfoRepository recruitInfoRepository;
    private final RecruitPostRepository recruitPostRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public RecruitInfoServiceImpl(RecruitInfoRepository recruitInfoRepository,
                                  MemberRepository memberRepository,
                                  RecruitPostRepository recruitPostRepository) {
        this.recruitInfoRepository = recruitInfoRepository;
        this.memberRepository = memberRepository;
        this.recruitPostRepository = recruitPostRepository;
    }

    // 게시글 생성
    @Override
    @Transactional
    public RecruitInfoDTO createRecruitInfo(RecruitInfoDTO recruitInfoDTO, Long parentPostId, CustomUserDetails customUserDetails) {
        String userId = customUserDetails.getUsername();

        Member member = memberRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("Member not found"));

        RecruitPost recruitPost = recruitPostRepository.findById(parentPostId)
                .orElseThrow(() -> new RuntimeException("RecruitPost not found"));

        RecruitInfo recruitInfo = RecruitInfoMapper.toEntity(recruitInfoDTO);
        recruitInfo.setMember(member);
        recruitInfo.setRecruitPost(recruitPost);

        recruitInfo.setState(State.WAITING);
        recruitInfo.setIsDeleted(false);

        recruitInfo = recruitInfoRepository.save(recruitInfo);

        return RecruitInfoMapper.toDto(recruitInfo);
    }


    // 부모 게시글에 딸린 모집 게시글 모두 불러오기
    @Override
    @Transactional(readOnly = true)
    public List<RecruitInfoDTO> getRecruitInfosByParentPostId(Long parentPostId) {
        List<RecruitInfo> recruitInfos = recruitInfoRepository.findByRecruitPostIdAndIsDeletedFalse(parentPostId);

        return recruitInfos.stream()
                .map(RecruitInfoMapper::toDto)
                .collect(Collectors.toList());
    }

    // 특정 유저가 작성한 모든 모집 게시글 불러오기
    @Override
    @Transactional(readOnly = true)
    public List<RecruitInfoDTO> getMyRecruitInfos(CustomUserDetails customUserDetails) {
        Long userId = Long.valueOf(customUserDetails.getUsername());
        List<RecruitInfo> recruitInfos = recruitInfoRepository.findByMemberIdAndIsDeletedFalse(userId);

        return recruitInfos.stream()
                .map(RecruitInfoMapper::toDto)
                .collect(Collectors.toList());
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
