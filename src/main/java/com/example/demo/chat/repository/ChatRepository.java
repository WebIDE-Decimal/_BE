package com.example.demo.chat.repository;

import com.example.demo.chat.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
}
