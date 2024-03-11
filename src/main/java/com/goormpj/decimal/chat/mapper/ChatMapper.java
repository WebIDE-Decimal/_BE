package com.goormpj.decimal.chat.mapper;

import com.goormpj.decimal.chat.dto.ChatMessageDTO;
import com.goormpj.decimal.chat.dto.ChatRoomDTO;
import com.goormpj.decimal.chat.model.ChatMessage;
import com.goormpj.decimal.chat.model.ChatRoom;

public class ChatMapper {
    public static ChatRoomDTO toChatRoomDTO(ChatRoom chatRoom) {
        return new ChatRoomDTO(chatRoom.getId(), chatRoom.getName(), chatRoom.getDescription());
    }

    public static ChatMessageDTO toChatMessageDTO(ChatMessage chatMessage) {
        return new ChatMessageDTO(chatMessage.getSenderID(), chatMessage.getContent(), chatMessage.getTimestamp(), chatMessage.getRoomId());
    }
}

