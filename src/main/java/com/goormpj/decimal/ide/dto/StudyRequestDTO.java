package com.goormpj.decimal.ide.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyRequestDTO {

    private String name;
    private int memberCount;
    private Long leaderId;
    private String recruitmentField;
    private List<Long> memberIds;

}