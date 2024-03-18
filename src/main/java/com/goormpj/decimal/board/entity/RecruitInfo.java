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


    // RecruitPost 관계성을 나타내는 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_post_id")       //RecruitPost 참조
    private RecruitPost recruitPost;

    //Member 관계성을 나타내는 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")             //Member 참조를 위한 필드와 mapping
    private Member member;

    private String detail;                      //자세한 설명
    private LocalDateTime updatedAt;            //마지막 업데이트 시간
    private Boolean isDeleted = false;          //기본값 false로 설정
}
