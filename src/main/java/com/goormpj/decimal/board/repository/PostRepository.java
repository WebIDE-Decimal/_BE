package com.goormpj.decimal.board.repository;

import com.goormpj.decimal.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
