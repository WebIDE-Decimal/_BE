package com.goormpj.decimal.board.mapper;

import com.goormpj.decimal.board.dto.RecruitPostDTO;
import com.goormpj.decimal.board.entity.RecruitPost;

public class RecruitPostMapper {

    // Entity-> DTO
    public static RecruitPostDTO entityToDto(RecruitPost recruitPost) {
        RecruitPostDTO dto = new RecruitPostDTO();
        dto.setId(recruitPost.getId());
        dto.setWriterId(recruitPost.getWriter().getId());
        dto.setTitle(recruitPost.getTitle());
        dto.setContent(recruitPost.getContent());
        dto.setRecruited(recruitPost.getRecruited());
        dto.setState(recruitPost.getState());
        dto.setTarget(recruitPost.getTarget());
        dto.setLocalDateTime(recruitPost.getLocalDateTime());
        dto.setIsDeleted(recruitPost.getIsDeleted());
        return dto;
    }

    // DTO를 Entity로 변환하는 메서드
    public static RecruitPost dtoToEntity(RecruitPostDTO dto) {
        RecruitPost recruitPost = new RecruitPost();
        recruitPost.setId(dto.getId());

        recruitPost.setTitle(dto.getTitle());
        recruitPost.setContent(dto.getContent());
        recruitPost.setRecruited(dto.getRecruited());
        recruitPost.setState(dto.getState());
        recruitPost.setTarget(dto.getTarget());
        recruitPost.setLocalDateTime(dto.getLocalDateTime());
        recruitPost.setIsDeleted(dto.getIsDeleted());
        return recruitPost;
    }
}
