package com.goormpj.decimal.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDTO {
    private Long senderID;
    private String content;
    private LocalDateTime timestamp;
    private Long roomId;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(Long senderID, String content, LocalDateTime timestamp, Long roomId) {
        this.senderID = senderID;
        this.content = content;
        this.timestamp = timestamp;
        this.roomId = roomId;
    }
}
