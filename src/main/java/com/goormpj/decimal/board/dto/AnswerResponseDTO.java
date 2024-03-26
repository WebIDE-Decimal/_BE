package com.goormpj.decimal.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AnswerResponseDTO {
    private Long id;                //답변 작성자 Id
    private String content;         //답변 내용
    private LocalDateTime createdAt;    //답변 작성 시간
}
