package com.goormpj.decimal.board.controller;

import com.goormpj.decimal.board.dto.RecruitInfoDTO;
import com.goormpj.decimal.board.service.RecruitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruitInfo")
public class RecruitInfoController {
    private final RecruitInfoService recruitInfoService;

    @Autowired
    public RecruitInfoController(RecruitInfoService recruitInfoService) {
        this.recruitInfoService = recruitInfoService;
    }

    // 모집 게시글 생성
    @PostMapping
    public ResponseEntity<RecruitInfoDTO> createRecruitInfo(@RequestBody RecruitInfoDTO recruitInfoDTO,
                                                            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        RecruitInfoDTO createdRecruitInfoDTO = recruitInfoService.createRecruitInfo(recruitInfoDTO);
        return new ResponseEntity<>(createdRecruitInfoDTO, HttpStatus.CREATED);
    }

    // 모집 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<RecruitInfoDTO> getRecruitInfo(@PathVariable Long id) {
        RecruitInfoDTO recruitInfoDTO = recruitInfoService.getRecruitInfoById(id);
        return ResponseEntity.ok(recruitInfoDTO);
    }

    // 모집 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecruitInfo(@PathVariable Long id) {
        recruitInfoService.deleteRecruitInfo(id);
        return ResponseEntity.ok().build();
    }

    // 모집 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<RecruitInfoDTO> updateRecruitInfo(@PathVariable Long id, @RequestBody RecruitInfoDTO recruitInfoDTO) {
        RecruitInfoDTO updatedRecruitInfoDTO = recruitInfoService.updateRecruitInfo(id, recruitInfoDTO);
        return ResponseEntity.ok(updatedRecruitInfoDTO);
    }

    // 모집 수락 혹은 거절
    @PatchMapping("/{id}/response")
    public ResponseEntity<Void> respondToRecruit(@PathVariable Long id, @RequestParam("accept") boolean accept) {
        recruitInfoService.respondToRecruit(id, accept);
        return ResponseEntity.ok().build();
    }

}


