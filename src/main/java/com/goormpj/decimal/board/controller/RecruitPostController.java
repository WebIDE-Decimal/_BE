package com.goormpj.decimal.board.controller;

import com.goormpj.decimal.board.dto.RecruitPostRequestDTO;
import com.goormpj.decimal.board.dto.RecruitPostResponseDTO;
import com.goormpj.decimal.board.service.RecruitPostService;
import com.goormpj.decimal.board.mapper.RecruitPostMapper;
import com.goormpj.decimal.board.entity.RecruitPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recruit")
public class RecruitPostController {
    private final RecruitPostService recruitPostService;

    @Autowired
    public RecruitPostController(RecruitPostService recruitPostService) {
        this.recruitPostService = recruitPostService;
    }

    // 모든 모집 게시글 조회
    @GetMapping
    public ResponseEntity<List<RecruitPostResponseDTO>> getAllRecruitPosts() {
        List<RecruitPostResponseDTO> dtos = recruitPostService.findAllNotDeleted().stream()
                .map(RecruitPostMapper::entityToResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")        // ID로 특정 모집 게시글 조회
    public ResponseEntity<RecruitPostResponseDTO> getRecruitPostById(@PathVariable Long id) {
        RecruitPostResponseDTO dto = RecruitPostMapper.entityToResponseDto(recruitPostService.findByIdNotDeleted(id));
        return ResponseEntity.ok(dto);
    }


    // 새 모집 게시글 생성
    @PostMapping
    public ResponseEntity<RecruitPostResponseDTO> createRecruitPost(@RequestBody RecruitPostRequestDTO requestDTO) {
        RecruitPost savedPost = recruitPostService.createRecruitPost(RecruitPostMapper.requestDtoToEntity(requestDTO));
        RecruitPostResponseDTO responseDTO = RecruitPostMapper.entityToResponseDto(savedPost);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    // 특정 모집 게시글을 업데이트하는 메서드
    @PutMapping("/{id}")
    public ResponseEntity<RecruitPostResponseDTO> updateRecruitPost(@PathVariable Long id,
                                                                    @RequestBody RecruitPostRequestDTO requestDTO) {
        RecruitPost updatedPost = recruitPostService.updateRecruitPost(id, RecruitPostMapper.requestDtoToEntity(requestDTO));
        RecruitPostResponseDTO responseDTO = RecruitPostMapper.entityToResponseDto(updatedPost);
        return ResponseEntity.ok(responseDTO);
    }

    // 특정 모집 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecruitPost(@PathVariable Long id) {
            recruitPostService.softDelete(id);
            return ResponseEntity.noContent().build(); // 게시글 삭제후 응답
    }


}
