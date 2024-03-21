package com.goormpj.decimal.ide.domain;

import com.goormpj.decimal.user.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Study {

    @Id @GeneratedValue
    @Column(name = "study_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "leader_id")
    private Member leader;

    private String field;

    private String name;

    private int memberCount;

    @OneToMany(mappedBy = "study")
    private List<StudyMember> members = new ArrayList<>();

    public Study() {
    }
}