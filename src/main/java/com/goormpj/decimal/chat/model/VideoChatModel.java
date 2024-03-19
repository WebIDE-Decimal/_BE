package com.goormpj.decimal.chat.model;

import com.goormpj.decimal.user.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "video-chat")
@Getter
@Setter
public class VideoChatModel {
    @Id
    private String sessionId;

    private String customSessionId;

    @ManyToOne
    @JoinColumn(name = "member_id") // Member 엔티티와의 관계를 정의합니다.
    private Member member;

}
