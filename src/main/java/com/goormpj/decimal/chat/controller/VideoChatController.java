package com.goormpj.decimal.chat.controller;

import com.goormpj.decimal.chat.dto.VideoChatDto;
import com.goormpj.decimal.chat.repository.VideoChatRepository;
import com.goormpj.decimal.chat.service.VideoChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/chat")
public class VideoChatController {

    private final VideoChatService videoChatService;

    @Autowired
    public VideoChatController(VideoChatService videoChatService) {
        this.videoChatService = videoChatService;
    }

    // 세션 생성
    @PostMapping("/sessions")
    public ResponseEntity<String> initializeSession(@RequestBody(required = false) VideoChatDto videoChatDto) {
        try {
            String sessionId = videoChatService.initializeSession(videoChatDto);
            return ResponseEntity.ok(sessionId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 세션 연결
    @PostMapping("/sessions/{sessionId}/connections")
    public ResponseEntity<String> createConnection(@PathVariable String sessionId, @RequestBody(required = false) VideoChatDto videoChatDto) {
        try {
            String token = videoChatService.createConnection(sessionId, videoChatDto);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // 사용자가 연결된 모든 세션 가져오기
    @GetMapping("/{userId}/sessions")
    public ResponseEntity<List<VideoChatDto>> getUserSessions(@PathVariable String userId) {
        try {
            Long memberId = Long.parseLong(userId);
            List<VideoChatDto> sessions = videoChatService.getUserSessions(memberId);
            if (sessions.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
