package com.goormpj.decimal.board.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.goormpj.decimal.board.entity.RecruitPost;
import com.goormpj.decimal.board.repository.RecruitPostRepository;
import com.goormpj.decimal.user.domain.Member;
import com.goormpj.decimal.user.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RecruitPostServiceImplTest {

    @Mock
    private RecruitPostRepository recruitPostRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private RecruitPostServiceImpl recruitPostService;

    private RecruitPost existingPost;
    private Member member;

    @BeforeEach
    public void setUp() {
        member = Member.builder()
                .id(1L)
                .email("test@example.com")
                .nickname("tester")
                .password("securepassword")
                .build();

        existingPost = new RecruitPost();
        existingPost.setId(1L);
        existingPost.setTitle("Old Post");
        existingPost.setContent("Old content");
        existingPost.setWriter(member);
        existingPost.setRecruited(0);
        existingPost.setState(true);
        existingPost.setTarget("Everyone");

        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
        when(recruitPostRepository.findByIdAndIsDeletedFalse(anyLong())).thenReturn(Optional.of(existingPost));
        when(recruitPostRepository.save(any(RecruitPost.class))).thenReturn(existingPost);
    }

    @Test
    public void testUpdateRecruitPost() {
        RecruitPost updateInfo = new RecruitPost();
        updateInfo.setTitle("Updated Post");
        updateInfo.setContent("Updated content");
        updateInfo.setRecruited(5);
        updateInfo.setState(false);
        updateInfo.setTarget("Developers");

        // 테스트 실행: RecruitPost 업데이트
        RecruitPost updatedPost = recruitPostService.updateRecruitPost(1L, updateInfo);

        // 검증: 업데이트된 Post가 null이 아닌지 확인
        assertNotNull(updatedPost, "The updated post should not be null");

        // 검증: 업데이트된 Post의 필드 값이 예상과 일치하는지 확인
        assertEquals("Updated Post", updatedPost.getTitle(), "The title of the updated post should match the expected value");
        assertEquals("Updated content", updatedPost.getContent(), "The content of the updated post should match the expected value");
        assertEquals(Integer.valueOf(5), updatedPost.getRecruited(), "The number of recruited of the updated post should match the expected value");
        assertFalse(updatedPost.getState(), "The state of the updated post should be false");
        assertEquals("Developers", updatedPost.getTarget(), "The target of the updated post should match the expected value");

        // Mockito를 사용하여 save 메서드가 정확히 한 번 호출되었는지 확인
        verify(recruitPostRepository, times(1)).save(any(RecruitPost.class));
    }
}
