package com.goormpj.decimal.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String content;

    // 기본 생성자
    public PostDTO() {}

    // 모든 필드를 포함한 생성자
    public PostDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
