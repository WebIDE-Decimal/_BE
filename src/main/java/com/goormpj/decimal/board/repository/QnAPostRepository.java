package com.goormpj.decimal.board.repository;

import com.goormpj.decimal.board.entity.QnAPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface QnAPostRepository extends JpaRepository<QnAPost, Long> {
    List<QnAPost> findByIsDeletedFalse();
    Optional<QnAPost> findByIdAndIsDeletedFalse(Long id);
}
