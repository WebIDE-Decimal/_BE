package com.goormpj.decimal.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignalMessageDTO {
    private Long userId;
    private String type; // "sdp" 또는 "ice"
    private String content; // SDP 문자열 또는 ICE 후보
    private String roomId; // 메시지가 속한 방의 ID

    public SignalMessageDTO() {
    }

    public SignalMessageDTO(Long userId, String type, String content, String roomId) {
        this.userId = userId;
        this.type = type;
        this.content = content;
        this.roomId = roomId;
    }
}
