package com.example.demo.chat.controller;

import com.example.demo.chat.model.ChatMessage;
import com.example.demo.chat.service.MessageService;
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
    public ChatMessage sendMessage(@DestinationVariable Long roomId, ChatMessage chatMessage) {
        Long senderID = chatMessage.getSenderID();
        String messageContent = chatMessage.getContent();

        // MessageService를 사용하여 메시지 저장
        messageService.sendMessage(roomId, senderID, messageContent);

        return chatMessage;
    }
}
