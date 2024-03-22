package com.goormpj.decimal.board.mapper;

import com.goormpj.decimal.board.dto.RecruitPostRequestDTO;
import com.goormpj.decimal.board.dto.RecruitPostResponseDTO;
import com.goormpj.decimal.board.entity.RecruitPost;

public class RecruitPostMapper {

    public static RecruitPost requestDtoToEntity(RecruitPostRequestDTO requestDTO) {
        RecruitPost recruitPost = new RecruitPost();
        recruitPost.setTitle(requestDTO.getTitle());
        recruitPost.setContent(requestDTO.getContent());
        recruitPost.setRecruited(requestDTO.getRecruited());
        recruitPost.setState(requestDTO.getState());
        recruitPost.setTarget(requestDTO.getTarget());

        return recruitPost;
    }

    public static RecruitPostResponseDTO entityToResponseDto(RecruitPost recruitPost) {
        return new RecruitPostResponseDTO(
                recruitPost.getId(),
                recruitPost.getTitle(),
                recruitPost.getContent(),
                recruitPost.getRecruited(),
                recruitPost.getState(),
                recruitPost.getTarget(),
                recruitPost.getCreatedAt(),
                recruitPost.getUpdatedAt(),
                recruitPost.getIsDeleted()
        );
    }
}