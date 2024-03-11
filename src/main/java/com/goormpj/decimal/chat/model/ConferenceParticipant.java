package com.goormpj.decimal.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ConferenceParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long roomId; // ConferenceRoom의 ID 참조
    private Long userId; // 사용자 ID 참조
    @Enumerated(EnumType.STRING)
    private ParticipantStatus status; // 참가자 상태

    public enum ParticipantStatus {
        WAITING,
        ACTIVE,
        INACTIVE
    }
    @CreatedDate
    private LocalDateTime joinedAt;
}
