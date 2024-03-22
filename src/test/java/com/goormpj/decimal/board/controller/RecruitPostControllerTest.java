package com.goormpj.decimal.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goormpj.decimal.board.entity.RecruitPost;
import com.goormpj.decimal.board.service.RecruitPostService;
import com.goormpj.decimal.user.domain.Authority;
import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.dto.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
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
    public void whenPostRecruit_thenCreateRecruitPost() throws Exception {
        // 테스트할 RecruitPost 관련 JSON 데이터 준비
        String jsonContent = objectMapper.writeValueAsString(post);

        // API 테스트 실행
        mockMvc.perform(post("/api/recruit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated());
    }
}
