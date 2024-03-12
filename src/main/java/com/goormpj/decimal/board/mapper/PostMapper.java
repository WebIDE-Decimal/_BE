package com.goormpj.decimal.board.mapper;

import com.goormpj.decimal.board.dto.PostDTO;
import com.goormpj.decimal.board.entity.Post;

public class PostMapper {

    public static PostDTO entityToDto(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        return postDTO;
    }

    public static Post dtoToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setId(post.getId());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        return post;
    }
}
