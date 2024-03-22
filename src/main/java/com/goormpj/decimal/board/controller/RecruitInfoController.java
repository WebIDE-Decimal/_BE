package com.goormpj.decimal.board.controller;

import com.goormpj.decimal.board.dto.RecruitInfoDTO;
import com.goormpj.decimal.board.dto.RecruitPostResponseDTO;
import com.goormpj.decimal.board.entity.RecruitInfo;
import com.goormpj.decimal.board.entity.RecruitPost;
import com.goormpj.decimal.board.service.RecruitInfoService;
import com.goormpj.decimal.board.service.RecruitPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruitInfo")
public class RecruitInfoController {
    private final RecruitInfoService recruitInfoService;
    private final RecruitPostService recruitPostService;

    @Autowired
    public RecruitInfoController(RecruitInfoService recruitInfoService, RecruitPostService recruitPostService)
    {
        this.recruitInfoService = recruitInfoService;
        this.recruitPostService = recruitPostService;
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<RecruitInfo> getRecruitInfoById(@PathVariable Long id) {
        RecruitInfo recruitInfo = recruitInfoService.findById(id);
        if (recruitInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recruitInfo);
    }

    @PostMapping
    public ResponseEntity<RecruitInfo> createRecruitInfo(@RequestBody RecruitInfoDTO recruitInfoDTO) {
        RecruitPostResponseDTO recruitPostResponse = recruitPostService.findByIdNotDeleted(recruitInfoDTO.getRecruitPostId());
        if (recruitPostResponse ==  null) {
            return ResponseEntity.badRequest().build();
        }

        RecruitPost recruitPost = new RecruitPost();
        recruitPost.setId(recruitPostResponse.getId());

        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setDetail(recruitInfoDTO.getDetail());
        recruitInfo.setRecruitPost(recruitPost);          //Recruitpost 연결 설정
        recruitInfo.setIsDeleted(recruitInfoDTO.getIsDeleted());

        RecruitInfo savedRecruitInfo = recruitInfoService.save(recruitInfo);
        return ResponseEntity.ok(savedRecruitInfo);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteRecruitInfo(@PathVariable Long id) {
        recruitInfoService.softDelete(id);                  //soft delete
        return ResponseEntity.ok().build();
    }

 */
}