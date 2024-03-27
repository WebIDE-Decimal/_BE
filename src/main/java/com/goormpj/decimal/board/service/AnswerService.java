package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.dto.AnswerRequestDTO;
import com.goormpj.decimal.board.dto.AnswerResponseDTO;
import java.util.List;

public interface AnswerService {
    AnswerResponseDTO addAnswerToQnAPost(Long qnaPostId, AnswerRequestDTO requestDTO, Long userId);
    AnswerResponseDTO updateAnswer(Long answerId, AnswerRequestDTO requestDTO, Long userId);

    void deleteAnswer(Long answerId, Long userId);

    List<AnswerResponseDTO> getAllAnswersForQnAPost(Long qnaPostId);
}
