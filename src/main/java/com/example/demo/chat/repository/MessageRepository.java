package com.example.demo.chat.repository;

import com.example.demo.chat.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByRoomId(Long roomId);
}