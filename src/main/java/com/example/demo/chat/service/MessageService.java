package com.example.demo.chat.service;

import com.example.demo.chat.model.ChatMessage;
import com.example.demo.chat.model.ChatMessage.MessageType;
import com.example.demo.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // 메시지 전송
    public ChatMessage sendMessage(Long roomId, Long userId, String messageContent) {
        ChatMessage message = new ChatMessage();
        message.setType(MessageType.CHAT);
        message.setContent(messageContent);
        message.setSenderID(userId);
        message.setRoomId(roomId);
        return messageRepository.save(message);
    }

    // 특정 채팅방의 모든 메시지 조회
    public List<ChatMessage> getMessagesByRoomId(Long roomId) {
        return messageRepository.findByRoomId(roomId);
    }
}