package com.example.demo.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    private Long roomId;
    private Long senderID;
    private String content;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
