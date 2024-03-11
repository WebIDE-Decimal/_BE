package com.goormpj.decimal.chat.repository;

import com.goormpj.decimal.chat.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
}
