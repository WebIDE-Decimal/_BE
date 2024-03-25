package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.dto.QnAPostRequestDTO;
import com.goormpj.decimal.board.dto.QnAPostResponseDTO;
import com.goormpj.decimal.board.entity.QnAPost;
import com.goormpj.decimal.board.mapper.QnAPostMapper;
import com.goormpj.decimal.board.repository.QnAPostRepository;
import com.goormpj.decimal.board.service.QnAPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class QnAPostServiceImpl implements QnAPostService{

    private final QnAPostRepository qnAPostRepository;

    @Autowired
    public QnAPostServiceImpl(QnAPostRepository qnaPostRepository) {
        this.qnAPostRepository = qnaPostRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QnAPostResponseDTO> findAll() {
        return qnAPostRepository.findByIsDeletedFalse().stream()
                .map(QnAPostMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public QnAPostResponseDTO findById(Long id) {
        QnAPost qnAPost = qnAPostRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IllegalArgumentException("QnA Post not found with id " + id));
        return QnAPostMapper.toResponseDTO(qnAPost);
    }

    @Override
    public QnAPostResponseDTO create(QnAPostRequestDTO requestDTO) {
        QnAPost qnAPost = QnAPostMapper.toEntity(requestDTO);
        qnAPost = qnAPostRepository.save(qnAPost);
        return QnAPostMapper.toResponseDTO(qnAPost);
    }

    @Override
    public QnAPostResponseDTO update(Long id, QnAPostRequestDTO requestDTO) {
        QnAPost qnAPost = qnAPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("QnA Post not found with id " + id));

        qnAPost.setTitle(requestDTO.getTitle());
        qnAPost.setContent(requestDTO.getContent());
        qnAPost = qnAPostRepository.save(qnAPost);

        return QnAPostMapper.toResponseDTO(qnAPost);
    }

    @Override
    public void delete(Long id) {
        QnAPost qnAPost = qnAPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("QnA Post not found with id " + id));

        qnAPost.setIsDeleted(true);
        qnAPostRepository.save(qnAPost);
    }
}
