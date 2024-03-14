package com.goormpj.decimal.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitPostDTO {
    private Long id; // 게시글 ID
    private Long writerId; // 작성자 ID
    private String title; // 제목
    private String content; // 내용
    private Integer recruited; // 모집 인원
    private String state; // 모집 상태
    private String target; // 모집 대상
    private LocalDateTime localDateTime; // 작성 시각
    private Boolean isDeleted; // 삭제 여부
}
