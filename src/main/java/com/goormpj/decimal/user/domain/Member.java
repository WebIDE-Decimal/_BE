package com.goormpj.decimal.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String nickname;

    private String password;

    private String profileFilename;

    private String profileFilepath;

}
