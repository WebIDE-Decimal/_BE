package com.goormpj.decimal.board.mapper;

import com.goormpj.decimal.board.dto.AnswerRequestDTO;
import com.goormpj.decimal.board.dto.AnswerResponseDTO;
import com.goormpj.decimal.board.entity.Answer;
public class AnswerMapper {

    //AnswerRequestDTO -> Answer ENTITY
    public static Answer toEntity(AnswerRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Answer answer = new Answer();
        answer.setContent(dto.getContent());

        return answer;
    }

    //Answer ENTITY -> AnswerResponseDTO
    public static AnswerResponseDTO toResponseDTO(Answer answer) {
        if (answer == null) {
            return null;
        }

        return new AnswerResponseDTO(
                answer.getId(),
                answer.getContent(),
                answer.getCreatedAt()
        );
    }
}
