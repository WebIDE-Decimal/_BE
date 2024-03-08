package com.example.demo.chat.controller;

import com.example.demo.chat.model.ChatMessage;
import com.example.demo.chat.model.ChatRoom;
import com.example.demo.chat.service.ChatService;
import com.example.demo.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;
    private final MessageService messageService;

    @Autowired
    public ChatController(ChatService chatService, MessageService messageService) {
        this.chatService = chatService;
        this.messageService = messageService;
    }

    // 채팅방 생성
    @PostMapping("/rooms")
    public ResponseEntity<ChatRoom> createChat(@RequestBody ChatRoom chatRoom) {
        ChatRoom newChatRoom = chatService.createChat(chatRoom.getName(), chatRoom.getDescription());
        return ResponseEntity.ok(newChatRoom);
    }

    // 모든 채팅방 조회
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoom>> getAllChats() {
        List<ChatRoom> chatRooms = chatService.getAllChats();
        return ResponseEntity.ok(chatRooms);
    }

    // 특정 채팅방의 모든 메시지 조회
    @GetMapping("/messages/{roomId}")
    public ResponseEntity<List<ChatMessage>> getMessagesByRoomId(@PathVariable Long roomId) {
        List<ChatMessage> messages = messageService.getMessagesByRoomId(roomId);
        return ResponseEntity.ok(messages);
    }
}