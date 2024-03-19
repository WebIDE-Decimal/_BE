package com.goormpj.decimal.board.mapper;

import com.goormpj.decimal.board.dto.RecruitInfoDTO;
import com.goormpj.decimal.board.entity.RecruitInfo;
import com.goormpj.decimal.board.entity.RecruitPost;

public class RecruitInfoMapper {

    //Recruitinfo -> RecruitinfoDTO
    public static RecruitInfoDTO entityToDto(RecruitInfo recruitInfo) {
        RecruitInfoDTO dto = new RecruitInfoDTO();
        dto.setDetail(recruitInfo.getDetail());
        if (recruitInfo.getRecruitPost() !=null) {
            dto.setRecruitPostId(recruitInfo.getRecruitPost().getId());
        }
        dto.setIsDeleted(recruitInfo.getIsDeleted());
        return dto;
    }

    //RecruitInfoDTO -> RecruitInfo
    public static RecruitInfo dtoToEntity(RecruitInfoDTO dto) {
        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setDetail(dto.getDetail());

        recruitInfo.setIsDeleted(dto.getIsDeleted());
        return recruitInfo;
    }
}
