package com.goormpj.decimal.board.controller;

import com.goormpj.decimal.board.dto.RecruitPostRequestDTO;
import com.goormpj.decimal.board.dto.RecruitPostResponseDTO;
import com.goormpj.decimal.board.entity.RecruitPost;
import com.goormpj.decimal.board.service.RecruitPostService;
import com.goormpj.decimal.user.domain.Authority;
import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.dto.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RecruitPostController.class)
public class RecruitPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecruitPostService recruitPostService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private RecruitPost post;

    @BeforeEach
    public void setUp() {
        Member writer = new Member(1L, "test@example.com", "tester", "securepassword", "profile.png", "/path/to/profile", Authority.ROLE_USER);
        RecruitPost post = new RecruitPost("Sample Post", "Sample content", writer, false, 3, true, "Everyone");

        // createRecruitPost 메서드 모킹을 수정하여, anyString()을 사용
        when(recruitPostService.createRecruitPost(any(RecruitPost.class), anyString())).thenReturn(post);
    }

    @Test
    @WithMockUser
    public void createRecruitPostTest() throws Exception {
        RecruitPostRequestDTO requestDTO = new RecruitPostRequestDTO("Test Title", "Test Content", 3, "Target");
        RecruitPostResponseDTO responseDTO = new RecruitPostResponseDTO(1L, "Test Title", "Test Content", 3, false, "Target", LocalDateTime.now(), LocalDateTime.now(), false);

        given(recruitPostService.createRecruitPost(any(RecruitPost.class), anyString())).willReturn(new RecruitPost());

        mockMvc.perform(post("/api/recruit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk()) // 상태 코드가 의도한 대로 201 Created 등으로 변경해야 할 수 있습니다.
                .andExpect(jsonPath("$.title").value(responseDTO.getTitle()))
                .andExpect(jsonPath("$.content").value(responseDTO.getContent()))
                .andExpect(jsonPath("$.recruited").value(responseDTO.getRecruited()))
                .andExpect(jsonPath("$.state").value(responseDTO.getState()))
                .andExpect(jsonPath("$.target").value(responseDTO.getTarget()));
    }
}
