package com.goormpj.decimal.chat.controller;

import com.goormpj.decimal.chat.dto.ChatMessageDTO;
import com.goormpj.decimal.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/{roomId}/sendMessage")
    @SendTo("/topic/rooms/{roomId}")
    public ChatMessageDTO sendMessageToRoom(@DestinationVariable Long roomId, ChatMessageDTO chatMessageDTO) {
        messageService.sendMessage(roomId, chatMessageDTO.getSenderID(), chatMessageDTO.getContent());
        return chatMessageDTO;
    }
}