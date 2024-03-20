package com.goormpj.decimal.board.repository;

import com.goormpj.decimal.board.entity.RecruitInfo;
import com.goormpj.decimal.board.entity.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitInfoRepository extends JpaRepository<RecruitInfo, Long> {
    List<RecruitInfo> findByIsDeletedFalse();       //soft delete 않은 데이터 조회

}