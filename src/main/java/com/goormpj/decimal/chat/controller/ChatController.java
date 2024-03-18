package com.goormpj.decimal.chat.controller;

import com.goormpj.decimal.chat.dto.ChatRoomDTO;
import com.goormpj.decimal.chat.dto.ChatMessageDTO;
import com.goormpj.decimal.chat.mapper.ChatMapper;
import com.goormpj.decimal.chat.model.ChatMessage;
import com.goormpj.decimal.chat.model.ChatRoom;
import com.goormpj.decimal.chat.service.ChatService;
import com.goormpj.decimal.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<ChatRoomDTO> createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        ChatRoom createdChatRoom = chatService.createChat(chatRoomDTO.getName(), chatRoomDTO.getDescription());
        return ResponseEntity.ok(ChatMapper.toChatRoomDTO(createdChatRoom));
    }

    // 채팅방 목록 조회
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomDTO>> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatService.getAllChats();
        List<ChatRoomDTO> chatRoomDTOs = chatRooms.stream().map(ChatMapper::toChatRoomDTO).collect(Collectors.toList());
        return ResponseEntity.ok(chatRoomDTOs);
    }

    // 특정 채팅방 메시지 조회
    @GetMapping("/messages/{roomId}")
    public ResponseEntity<List<ChatMessageDTO>> getMessagesByRoomId(@PathVariable Long roomId) {
        List<ChatMessage> messages = messageService.getMessagesByRoomId(roomId);
        List<ChatMessageDTO> messageDTOs = messages.stream().map(ChatMapper::toChatMessageDTO).collect(Collectors.toList());
        return ResponseEntity.ok(messageDTOs);
    }

}
