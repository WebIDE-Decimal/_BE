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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
