package com.goormpj.decimal.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor

public class RecruitInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPost;            //RecruitPost와 엔티티 연결

    private String detail;                      //자세한 설명
    private LocalDateTime updatedAt;            //마지막 업데이트 시간
    private Boolean isDeleted = false;          //기본값 false로 설정
}
