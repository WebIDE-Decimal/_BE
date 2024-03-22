package com.goormpj.decimal.board.repository;

import com.goormpj.decimal.board.entity.RecruitInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecruitInfoRepository extends JpaRepository<RecruitInfo, Long> {
    Optional<RecruitInfo> findByIdAndIsDeletedFalse(Long id);
}