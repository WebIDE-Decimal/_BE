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
        message.setType(MessageType.CHAT); // 메시지 타입 설정
        message.setContent(messageContent); // 메시지 내용
        message.setSenderID(userId); // 보내는 사람의 ID
        message.setRoomId(roomId); // 채팅방 ID 설정
        return messageRepository.save(message);
    }

    // 특정 채팅방의 모든 메시지 조회
    public List<ChatMessage> getMessagesByRoomId(Long roomId) {
        return messageRepository.findByRoomId(roomId);
    }
}