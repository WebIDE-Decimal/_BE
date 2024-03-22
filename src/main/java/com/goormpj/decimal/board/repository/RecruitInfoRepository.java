package com.goormpj.decimal.board.repository;

import com.goormpj.decimal.board.entity.RecruitInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecruitInfoRepository extends JpaRepository<RecruitInfo, Long> {
    List<RecruitInfo> findByRecruitPostIdAndIsDeletedFalse(Long recruitPostId);
    List<RecruitInfo> findByMemberIdAndIsDeletedFalse(Long memberId);
    List<RecruitInfo> findByMemberId(Long memberId);

}