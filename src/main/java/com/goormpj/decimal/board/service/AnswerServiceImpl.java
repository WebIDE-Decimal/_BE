package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.dto.AnswerRequestDTO;
import com.goormpj.decimal.board.dto.AnswerResponseDTO;
import com.goormpj.decimal.board.entity.Answer;
import com.goormpj.decimal.board.mapper.AnswerMapper;
import com.goormpj.decimal.board.entity.QnAPost;
import com.goormpj.decimal.board.repository.AnswerRepository;
import com.goormpj.decimal.board.repository.QnAPostRepository;
import com.goormpj.decimal.board.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public List<AnswerResponseDTO> getAllAnswersForQnAPost(Long qnaPostId) {
        List<Answer> answers = answerRepository.findByQnaPostId(qnaPostId);
        return answers.stream()
                .map(AnswerMapper::toResponseDTO)
                .collect(Collectors.toList());

    }


    @Override
    public AnswerResponseDTO addAnswerToQnAPost(Long qnaPostId, AnswerRequestDTO requestDTO, Long userId) {
        QnAPost qnaPost = qnaPostRepository.findById(qnaPostId)
                .orElseThrow(() -> new IllegalArgumentException("QnA Post not found with id: " + qnaPostId));
        Answer answer = new Answer();
        answer.setQnaPost(qnaPost);

        answer.setContent((requestDTO.getContent()));
        Answer savedAnswer = answerRepository.save(answer);

        return AnswerMapper.toResponseDTO(savedAnswer);
    }
    @Override
    public AnswerResponseDTO updateAnswer(Long answerId, AnswerRequestDTO requestDTO, Long userId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new IllegalArgumentException("Answer not found with id: " + answerId));

        if (!answer.getWriter().getId().equals(userId)) {
            throw new SecurityException("You do not have permission to update this answer.");
        }

        answer.setContent(requestDTO.getContent());
        Answer savedAnswer = answerRepository.save(answer);

        return AnswerMapper.toResponseDTO(savedAnswer);
    }

    @Override
    public void deleteAnswer(Long answerId, Long userId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new IllegalArgumentException("Answer not found with id: " + answerId));

        if(!answer.getWriter().getId().equals(userId)) {
            throw new SecurityException("You do not have permission to delete this answer.");
        }

        answerRepository.delete(answer);
    }
}
