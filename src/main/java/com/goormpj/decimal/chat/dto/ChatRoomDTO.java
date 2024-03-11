package com.goormpj.decimal.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDTO {
    private Long id;
    private String name;
    private String description;

    public ChatRoomDTO() {
    }

    public ChatRoomDTO(Long id,String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

