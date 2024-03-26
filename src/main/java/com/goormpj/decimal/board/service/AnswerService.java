package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.dto.AnswerRequestDTO;
import com.goormpj.decimal.board.dto.AnswerResponseDTO;

public interface AnswerService {
    AnswerResponseDTO addAnswerToQnAPost(Long qnaPostId, AnswerRequestDTO requestDTO);
}
