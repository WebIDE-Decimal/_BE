package com.goormpj.decimal.board.entity;
import com.goormpj.decimal.user.domain.Member;  // 경로 변경
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecruitPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 게시글 ID
    @ManyToOne
    @JoinColumn(name = "writer_id", referencedColumnName = "id")
    private Member writer; // 작성자 ID
    private String title; // 제목
    private String content; // 내용
    private Integer recruited; // 모집 인원
    private String state; // 모집 상태
    private String target; // 모집 대상
    private LocalDateTime localDateTime; // 작성 시각
    private Boolean isDeleted; // 삭제 여부
}