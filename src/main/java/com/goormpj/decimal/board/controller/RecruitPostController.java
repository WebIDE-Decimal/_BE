package com.goormpj.decimal.board.controller;

import com.goormpj.decimal.board.dto.RecruitPostDTO;
import com.goormpj.decimal.board.entity.RecruitPost;
import com.goormpj.decimal.board.mapper.RecruitPostMapper;
import com.goormpj.decimal.board.service.RecruitPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.repository.MemberRepository;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recruit")
public class RecruitPostController {
    private final RecruitPostService recruitPostService;
    private final MemberRepository memberRepository;    //memberRepositiry 추가

    @Autowired
    public RecruitPostController(RecruitPostService recruitPostService, MemberRepository memberRepository) {
        this.recruitPostService = recruitPostService;
        this.memberRepository = memberRepository;
    }

    // 모든 모집 게시글 조회
    @GetMapping
    public ResponseEntity<List<RecruitPostDTO>> getAllRecruitPosts() {
        List<RecruitPost> posts = recruitPostService.findAllNotDeleted();
        List<RecruitPostDTO> dtos = posts.stream()
                .map(RecruitPostMapper::entityToDto) // Entity -> DTO
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // 새 모집 게시글 생성
    @PostMapping
    public ResponseEntity<RecruitPostDTO> createRecruitPost(@RequestBody RecruitPostDTO recruitPostDTO) {
        // writerId를 사용하여 Member 엔티티 조회
        Optional<Member> writerOptional = memberRepository.findById(recruitPostDTO.getWriterId());
        if (!writerOptional.isPresent()) {
            // Member가 존재하지 않는 경우, 적절한 오류 처리
            return ResponseEntity.badRequest().body(null); // 또는 다른 오류 응답
        }

        // DTO에서 엔티티로 변환 (이미 존재하는 로직)
        RecruitPost recruitPost = RecruitPostMapper.dtoToEntity(recruitPostDTO);

        // writer 설정
        recruitPost.setWriter(writerOptional.get());
        // 엔티티 저장
        RecruitPost savedRecruitPost = recruitPostService.save(recruitPost);

        // 저장된 엔티티를 다시 DTO로 변환하여 반환
        RecruitPostDTO savedDto = RecruitPostMapper.entityToDto(savedRecruitPost);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    // ID로 특정 모집 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<RecruitPostDTO> getRecruitPostById(@PathVariable Long id) {
        return recruitPostService.findByIdNotDeleted(id)
                .map(RecruitPostMapper::entityToDto) // Entity -> DTO
                .map(dto -> ResponseEntity.ok(dto)) // DTO 변환후 표시
                .orElseGet(() -> ResponseEntity.notFound().build()); // 게시글 찾을 수 없는 경우 404 반환
    }

    // 특정 모집 게시글을 업데이트하는 메서드
    @PutMapping("/{id}")
    public ResponseEntity<RecruitPostDTO> updateRecruitPost(@PathVariable Long id, @RequestBody RecruitPostDTO recruitPostDTO) {
        return recruitPostService.findByIdNotDeleted(id).map(existingPost -> {
            RecruitPost updatedPost = RecruitPostMapper.dtoToEntity(recruitPostDTO); // DTO-> Entity
            updatedPost.setId(id); // ID 설정
            updatedPost = recruitPostService.save(updatedPost); // 업데이트 게시글 저장
            return ResponseEntity.ok(RecruitPostMapper.entityToDto(updatedPost)); // 게시글 저장후 DTO 변환
        }).orElseGet(() -> ResponseEntity.notFound().build()); // 게시글 찾을 수 없는 경우 404 반환
    }

    // 특정 모집 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecruitPost(@PathVariable Long id) {
            recruitPostService.softDelete(id);
            return ResponseEntity.noContent().build(); // 게시글 삭제후 응답
    }
}
