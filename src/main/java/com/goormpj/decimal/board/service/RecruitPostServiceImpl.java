package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.entity.RecruitPost;
import com.goormpj.decimal.board.repository.RecruitPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecruitPostServiceImpl implements RecruitPostService {

    private final RecruitPostRepository recruitPostRepository;

    @Autowired
    public RecruitPostServiceImpl(RecruitPostRepository recruitPostRepository) {
        this.recruitPostRepository = recruitPostRepository;
    }

    // 모든 모집 게시글 조회
    @Override
    public List<RecruitPost> findAll() {
        return recruitPostRepository.findAll();
    }

    // ID로 모집 게시글 조회
    @Override
    public Optional<RecruitPost> findById(Long id) {
        return recruitPostRepository.findById(id);
    }

    // 모집 게시글 저장
    @Override
    public RecruitPost save(RecruitPost recruitPost) {
        return recruitPostRepository.save(recruitPost);
    }

    // 모집 게시글 삭제
    @Override
    public void delete(Long id) {
        recruitPostRepository.deleteById(id);
    }
}