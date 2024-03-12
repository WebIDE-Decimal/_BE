package com.goormpj.decimal.board.service;

import com.goormpj.decimal.board.entity.Post;
import com.goormpj.decimal.board.repository.PostRepository;
import com.goormpj.decimal.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 모든 게시글 불러오기
    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 게시글 id로 찾기
    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    // 게시글 저장
    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    // 게시글 업데이트
    @Override
    public Post update(Long id, Post postDetails) {
        return postRepository.findById(id).map(existingPost -> {
            existingPost.setTitle(postDetails.getTitle());
            existingPost.setContent(postDetails.getContent());
            return postRepository.save(existingPost);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with id " + id));
    }

    // 게시글 삭제
    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
