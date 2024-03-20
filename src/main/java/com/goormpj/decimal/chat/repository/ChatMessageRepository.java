package com.goormpj.decimal.chat.repository;

import com.goormpj.decimal.chat.model.ChatMessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageModel, Long> {
    @Query("SELECT c FROM ChatMessageModel c WHERE c.session.sessionId = :sessionId")
    List<ChatMessageModel> findBySessionId(String sessionId);
}
