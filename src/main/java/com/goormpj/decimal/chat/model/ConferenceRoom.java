package com.goormpj.decimal.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ConferenceRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @CreatedDate
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private ConferenceType conferenceType; // 화상회의 또는 음성회의 구분

    public enum ConferenceType {
        VIDEO,
        AUDIO
    }
}