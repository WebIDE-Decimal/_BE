package com.goormpj.decimal.board.controller;

import com.goormpj.decimal.board.dto.AnswerRequestDTO;
import com.goormpj.decimal.board.dto.AnswerResponseDTO;
import com.goormpj.decimal.board.service.AnswerService;
import com.goormpj.decimal.user.dto.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qna/{qnaPostId}/answers")

public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    //답변 추가
    @PostMapping
    public ResponseEntity<AnswerResponseDTO> addAnswer
    (@PathVariable Long qnaPostId,
     @RequestBody AnswerRequestDTO answerDTO,
    @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        AnswerResponseDTO createdAnswer = answerService.addAnswerToQnAPost(qnaPostId, answerDTO, userId);
        return new ResponseEntity<>(createdAnswer, HttpStatus.CREATED);
    }

    //특정 질문에 대한 모든 답변 조회
    @GetMapping
    public ResponseEntity<List<AnswerResponseDTO>> getAllAnswersForQnAPost(@PathVariable Long qnaPostId) {
        List<AnswerResponseDTO> answers = answerService.getAllAnswersForQnAPost(qnaPostId);
        return ResponseEntity.ok(answers);
    }

    //답변 게시글 수정
    @PutMapping("/{answerId}")
    public ResponseEntity<AnswerResponseDTO> updateAnswer(@PathVariable Long answerId,
                                                          @RequestBody AnswerRequestDTO answerDTO,
                                                            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        AnswerResponseDTO updatedAnswer = answerService.updateAnswer(answerId, answerDTO, userId);
        return ResponseEntity.ok(updatedAnswer);
    }

    //답변 게시글 삭제
    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId,
                                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        answerService.deleteAnswer(answerId, userId);
        return ResponseEntity.noContent().build();}

}
