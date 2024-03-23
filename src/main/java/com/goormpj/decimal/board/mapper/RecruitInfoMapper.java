package com.goormpj.decimal.board.mapper;

import com.goormpj.decimal.board.dto.RecruitInfoDTO;
import com.goormpj.decimal.board.entity.RecruitInfo;

public class RecruitInfoMapper {

    public static RecruitInfoDTO toDto(RecruitInfo recruitInfo) {
        if (recruitInfo == null) {
            return null;
        }

        RecruitInfoDTO dto = new RecruitInfoDTO();
        dto.setMotivation(recruitInfo.getMotivation());
        return dto;
    }

    public static RecruitInfo toEntity(RecruitInfoDTO dto) {
        if (dto == null) {
            return null;
        }

        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setMotivation(dto.getMotivation());
        return recruitInfo;
    }
}
