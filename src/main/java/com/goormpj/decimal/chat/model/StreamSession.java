package com.goormpj.decimal.chat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class StreamSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long roomId; // ConferenceRoom의 ID 참조
    private String streamId; // 스트리밍 세션을 식별하는 ID
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}