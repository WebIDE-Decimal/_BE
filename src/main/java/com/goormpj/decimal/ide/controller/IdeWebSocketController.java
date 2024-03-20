package com.goormpj.decimal.ide.controller;

import com.goormpj.decimal.ide.domain.EditorChangeModel;
import com.goormpj.decimal.ide.dto.EditorChangeDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class IdeWebSocketController {
    @MessageMapping("/change")
    @SendTo("/ide/changes")
    public EditorChangeDto sendMessage(EditorChangeDto editorChangeDto) {
        return editorChangeDto;
    }

}
