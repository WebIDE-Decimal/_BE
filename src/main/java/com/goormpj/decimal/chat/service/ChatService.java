package com.goormpj.decimal.chat.service;

import com.goormpj.decimal.chat.model.ChatRoom;
import com.goormpj.decimal.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    // 채팅방 생성
    public ChatRoom createChat(String name, String description) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        chatRoom.setDescription(description);
        chatRoom.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chatRoom);
    }

    // 모든 채팅방 조회
    public List<ChatRoom> getAllChats() {
        return chatRepository.findAll();
    }

}