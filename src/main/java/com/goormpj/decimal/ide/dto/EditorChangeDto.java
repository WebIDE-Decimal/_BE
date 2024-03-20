package com.goormpj.decimal.ide.dto;

import lombok.Data;

@Data
public class EditorChangeDto {
    private String content; // 변경된 내용
    private int position; // 변경이 발생한 위치
}
