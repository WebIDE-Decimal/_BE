package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.dto.AnswerRequestDTO;
import com.goormpj.decimal.board.dto.AnswerResponseDTO;
import com.goormpj.decimal.board.entity.Answer;
import com.goormpj.decimal.board.entity.QnAPost;
import com.goormpj.decimal.board.mapper.AnswerMapper;
import com.goormpj.decimal.board.repository.AnswerRepository;
import com.goormpj.decimal.board.repository.QnAPostRepository;
import com.goormpj.decimal.board.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QnAPostRepository qnaPostRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, QnAPostRepository qnaPostRepository) {
        this.answerRepository = answerRepository;
        this.qnaPostRepository = qnaPostRepository;
    }

    @Override
    @Transactional
    public AnswerResponseDTO addAnswerToQnAPost(Long qnaPostId, AnswerRequestDTO requestDTO) {
        QnAPost qnaPost = qnaPostRepository.findById(qnaPostId)
                .orElseThrow(() -> new IllegalArgumentException("QnA Post not found with id: " + qnaPostId));

        Answer answer = AnswerMapper.toEntity(requestDTO);
        answer.setQnaPost(qnaPost);
        Answer savedAnswer = answerRepository.save(answer);

        return AnswerMapper.toResponseDTO(savedAnswer);
    }
}
