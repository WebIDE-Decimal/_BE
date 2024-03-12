package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAll();
    Optional<Post> findById(Long id);
    Post save(Post post);
    Post update(Long id, Post post);
    void delete(Long id);
}
