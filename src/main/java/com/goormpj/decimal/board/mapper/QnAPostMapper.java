package com.goormpj.decimal.board.mapper;

import com.goormpj.decimal.board.dto.QnAPostRequestDTO;
import com.goormpj.decimal.board.dto.QnAPostResponseDTO;
import com.goormpj.decimal.board.entity.QnAPost;



public class QnAPostMapper {

    public static QnAPost toEntity(QnAPostRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        QnAPost qnAPost = new QnAPost();
        qnAPost.setTitle(dto.getTitle());
        qnAPost.setContent(dto.getContent());

        return qnAPost;
    }

    public static QnAPostResponseDTO toResponseDTO(QnAPost entity) {
        if (entity == null) {
            return null;
        }

        QnAPostResponseDTO dto = new QnAPostResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
