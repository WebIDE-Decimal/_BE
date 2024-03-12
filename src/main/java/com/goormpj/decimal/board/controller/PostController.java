package com.goormpj.decimal.board.controller;

import com.goormpj.decimal.board.dto.PostDTO;
import com.goormpj.decimal.board.entity.Post;
import com.goormpj.decimal.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.goormpj.decimal.board.mapper.PostMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<Post> posts = postService.findAll();
        List<PostDTO> postDTOs = posts.stream()
                .map(PostMapper::entityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(postDTOs);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        Post post = PostMapper.dtoToEntity(postDTO);
        post = postService.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostMapper.entityToDto(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with id " + id));
        return ResponseEntity.ok(PostMapper.entityToDto(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with id " + id));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post = postService.save(post);
        return ResponseEntity.ok(PostMapper.entityToDto(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
