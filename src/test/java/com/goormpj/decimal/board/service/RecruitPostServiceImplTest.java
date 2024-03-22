package com.goormpj.decimal.board.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.goormpj.decimal.board.entity.RecruitPost;
import com.goormpj.decimal.board.repository.RecruitPostRepository;
import com.goormpj.decimal.user.domain.Member;
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

    @InjectMocks
    private RecruitPostServiceImpl recruitPostService;

    private RecruitPost post;

    @BeforeEach
    public void setUp() {
        post = new RecruitPost();
        post.setId(1L);
        post.setTitle("Sample Post");
        post.setContent("Sample content");
        post.setRecruited(0);
        post.setState(true);
        post.setTarget("Everyone");
        post.setIsDeleted(false);

        when(recruitPostRepository.findById(1L)).thenReturn(Optional.of(post));
    }

    @Test
    public void testSoftDelete() {
        recruitPostService.softDelete(1L);

        // Verify that the repository's save method was called with a post that has isDeleted set to true
        verify(recruitPostRepository).save(argThat(savedPost -> savedPost.getIsDeleted().equals(true)));

        // Additionally, verify findById was called to ensure the post to delete is fetched
        verify(recruitPostRepository).findById(1L);
    }
}
