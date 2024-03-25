package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.dto.QnAPostRequestDTO;
import com.goormpj.decimal.board.dto.QnAPostResponseDTO;

import java.util.List;

public interface QnAPostService {

    List<QnAPostResponseDTO> findAll();
    QnAPostResponseDTO findById(Long id);
    QnAPostResponseDTO create(QnAPostRequestDTO requestDTO);
    QnAPostResponseDTO update(Long id, QnAPostRequestDTO requestDTO);

    void delete(Long id);
}
