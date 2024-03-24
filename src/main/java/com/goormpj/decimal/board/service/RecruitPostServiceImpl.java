package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.entity.RecruitPost;
import com.goormpj.decimal.board.repository.RecruitPostRepository;
import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitPostServiceImpl implements RecruitPostService {
    private final RecruitPostRepository recruitPostRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public RecruitPostServiceImpl(RecruitPostRepository recruitPostRepository,
                                  MemberRepository memberRepository) {
        this.recruitPostRepository = recruitPostRepository;
        this.memberRepository = memberRepository;
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
    public RecruitPost createRecruitPost(RecruitPost recruitPost, Long userId) {
        Member writer = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with userId: " + userId));
        recruitPost.setState(true);
        recruitPost.setWriter(writer);
        return recruitPostRepository.save(recruitPost);
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
}