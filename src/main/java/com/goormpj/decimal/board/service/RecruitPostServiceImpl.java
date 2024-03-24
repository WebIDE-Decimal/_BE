package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.entity.RecruitPost;
<<<<<<< Updated upstream
=======
import com.goormpj.decimal.board.entity.RecruitInfo;
import com.goormpj.decimal.board.entity.State; // State 임포트
import com.goormpj.decimal.board.mapper.RecruitPostMapper;
>>>>>>> Stashed changes
import com.goormpj.decimal.board.repository.RecruitPostRepository;
import com.goormpj.decimal.board.repository.RecruitInfoRepository; // RecruitInfoRepository 임포트
import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitPostServiceImpl implements RecruitPostService {
    private final RecruitPostRepository recruitPostRepository;
    private final MemberRepository memberRepository;
    private final RecruitInfoRepository recruitInfoRepository;

    @Autowired
    public RecruitPostServiceImpl(RecruitPostRepository recruitPostRepository,
                                  MemberRepository memberRepository,
                                  RecruitInfoRepository recruitInfoRepository) {
        this.recruitPostRepository = recruitPostRepository;
        this.memberRepository = memberRepository;
        this.recruitInfoRepository = recruitInfoRepository;
    }


    @Override
    public List<RecruitPost> findAllNotDeleted() {
        return recruitPostRepository.findByIsDeletedFalse();
    }

    @Override
    public RecruitPost findByIdNotDeleted(Long id) {
        return recruitPostRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IllegalArgumentException("No such post found"));
    }

    @Override
<<<<<<< Updated upstream
    public RecruitPost createRecruitPost(RecruitPost recruitPost, String writerUsername) {
        Member writer = memberRepository.findById(Long.valueOf(writerUsername))
                .orElseThrow(() -> new IllegalArgumentException("Member not found with username: " + writerUsername));
        recruitPost.setWriter(writer);
        return recruitPostRepository.save(recruitPost);
=======
    public RecruitPost createRecruitPost(RecruitPostRequestDTO requestDTO, CustomUserDetails customUserDetails) {
        RecruitPost responsePost = RecruitPostMapper.requestDtoToEntity(requestDTO);

        Member writer = memberRepository.findById(Long.valueOf(customUserDetails.getUsername()))
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + customUserDetails.getUsername()));

        responsePost.setWriter(writer);
        return recruitPostRepository.save(responsePost);
>>>>>>> Stashed changes
    }
    @Override
    public RecruitPost updateRecruitPost(Long id, RecruitPost updateDetails) {
        RecruitPost recruitPost = recruitPostRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IllegalArgumentException("No such post found"));

        recruitPost.setTitle(updateDetails.getTitle());
        recruitPost.setContent(updateDetails.getContent());
        recruitPost.setRecruited(updateDetails.getRecruited());
        recruitPost.setState(updateDetails.getState());
        recruitPost.setTarget(updateDetails.getTarget());

        return recruitPostRepository.save(recruitPost);
    }


    @Override
    public void softDelete(Long id) {
        RecruitPost recruitPost = recruitPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with Id: " + id));
        recruitPost.setIsDeleted(true);
        recruitPostRepository.save(recruitPost);
    }

    //모집 상태 업데이트 메소드

    @Override
    public void updateRecruitmentState(Long postId, Boolean newState) {
        RecruitPost post = recruitPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with Id: " + postId));
        post.setState(newState);
        recruitPostRepository.save(post);
    }

    @Override
    public  void incrementCurrentRecruited(Long postId) {
        RecruitPost recruitPost = recruitPostRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with Id: " + postId));

        //현재 모집인원수 증가 후 저장
        recruitPost.setCurrentRecruited(recruitPost.getCurrentRecruited() + 1);
        recruitPostRepository.save(recruitPost);

    }

    @Override
    public void applyToRecruitPost(Long postId, CustomUserDetails userDetails) {
        Member applicant = memberRepository.findById(Long.parseLong(userDetails.getUsername()))
                .orElseThrow(() -> new EntityNotFoundException("Applicant not found"));

        RecruitPost post = recruitPostRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        // RecruitInfo 객체 생성 및 설정
        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setRecruitPost(post); // 지원하는 게시글
        recruitInfo.setMember(applicant); // 지원자를 writer 필드에 설정
        recruitInfo.setState(State.WAITING); // 지원 상태를 대기로 설정

        // RecruitInfo 저장
        recruitInfoRepository.save(recruitInfo);

        // 현재 모집 인원수 증가
        incrementCurrentRecruited(postId);
    }


}