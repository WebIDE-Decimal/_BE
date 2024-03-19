package com.goormpj.decimal.chat.mapper;

import com.goormpj.decimal.chat.dto.VideoChatDto;
import com.goormpj.decimal.chat.model.VideoChatModel;

public class VideoChatMapper {

    // VideoChatModel을 VideoChatDto로 변환
    public static VideoChatDto modelToDto(VideoChatModel model) {
        VideoChatDto dto = new VideoChatDto();
        dto.setMember(model.getMember());

        return dto;
    }

    // VideoChatDto를 VideoChatModel로 변환
    public static VideoChatModel dtotoModel(VideoChatDto dto) {
        VideoChatModel model = new VideoChatModel();
        model.setMember(dto.getMember());
        return model;
    }
}
