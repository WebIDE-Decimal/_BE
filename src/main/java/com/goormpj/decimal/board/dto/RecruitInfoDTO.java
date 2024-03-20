package com.goormpj.decimal.board.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RecruitInfoDTO {
    private String detail;
    private LocalDateTime updatedAt;
    private Long recruitPostId;         //연결될 RecruitPost의 id
    private Boolean isDeleted;
}
