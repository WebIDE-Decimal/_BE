package com.goormpj.decimal.board.repository;

import com.goormpj.decimal.board.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQnaPostId(Long qnaPostId);
}
