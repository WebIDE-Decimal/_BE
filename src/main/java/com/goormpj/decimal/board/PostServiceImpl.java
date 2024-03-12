package com.goormpj.decimal.board;

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

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post update(Long id, Post postDetails) {
        return postRepository.findById(id).map(existingPost -> {
            existingPost.setTitle(postDetails.getTitle());
            existingPost.setContent(postDetails.getContent());
            return postRepository.save(existingPost);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with id " + id));
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
