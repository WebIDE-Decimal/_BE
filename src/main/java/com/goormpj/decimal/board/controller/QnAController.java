package com.goormpj.decimal.board.controller;

import com.goormpj.decimal.board.dto.QnAPostRequestDTO;
import com.goormpj.decimal.board.dto.QnAPostResponseDTO;
import com.goormpj.decimal.board.service.QnAPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qna")

public class QnAController {

    private final QnAPostService qnAPostService;

    @Autowired
    public QnAController(QnAPostService qnaPostService) {
        this.qnAPostService = qnaPostService;
    }

    // 질문 생성
    @PostMapping
    public ResponseEntity<QnAPostResponseDTO> createQnAPost(@RequestBody QnAPostRequestDTO requestDTO) {
        QnAPostResponseDTO createdPost = qnAPostService.create(requestDTO);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    //질문 조회
    @GetMapping("/{id}")
    public ResponseEntity<QnAPostResponseDTO> getQnAPostById(@PathVariable Long id) {
        QnAPostResponseDTO post = qnAPostService.findById(id);
        return ResponseEntity.ok(post);
    }

    //질문 수정
    @PutMapping("/{id}")
    public ResponseEntity<QnAPostResponseDTO> updateQnAPost(@PathVariable Long id, @RequestBody QnAPostRequestDTO requestDTO) {
        QnAPostResponseDTO updatedPost = qnAPostService.update(id, requestDTO);
        return ResponseEntity.ok(updatedPost);
    }

    //질문 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQnAPost(@PathVariable Long id) {
        qnAPostService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


