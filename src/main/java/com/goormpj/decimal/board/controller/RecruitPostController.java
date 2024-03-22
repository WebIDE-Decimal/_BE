package com.goormpj.decimal.board.controller;

import com.goormpj.decimal.board.dto.RecruitPostRequestDTO;
import com.goormpj.decimal.board.dto.RecruitPostResponseDTO;
import com.goormpj.decimal.board.service.RecruitPostService;
import com.goormpj.decimal.board.mapper.RecruitPostMapper;
import com.goormpj.decimal.board.entity.RecruitPost;
import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication; // 추가

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recruit")
public class RecruitPostController {
    private final RecruitPostService recruitPostService;
    private final MemberService memberService;

    @Autowired
    public RecruitPostController(RecruitPostService recruitPostService, MemberService memberService) {
        this.recruitPostService = recruitPostService;
        this.memberService = memberService;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Member writer = memberService.findByEmail(userEmail);

        //state 상태 및 writer 설정
        RecruitPost recruitPost = RecruitPostMapper.requestDtoToEntity(requestDTO);
        recruitPost.setWriter(writer);
        recruitPost.setState(false);    // state 초기값
        RecruitPost savedPost = recruitPostService.createRecruitPost(recruitPost);

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

    @PatchMapping("/{id}/state")
    public ResponseEntity<Void> updatePostState(@PathVariable Long id, @RequestParam Boolean newState) {
        recruitPostService.updateRecruitmentState(id, newState);
        return ResponseEntity.ok().build();
    }

}
