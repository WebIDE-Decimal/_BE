package com.goormpj.decimal.ide.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class File {

    @Id
    @GeneratedValue
    @Column(name = "fild_id")
    private Long id;

    private String fileName;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private Folder folder;

}
