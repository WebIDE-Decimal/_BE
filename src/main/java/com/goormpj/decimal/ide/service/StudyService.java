package com.goormpj.decimal.ide.service;


import com.goormpj.decimal.ide.domain.Folder;
import com.goormpj.decimal.ide.domain.Study;

public interface StudyService {

    Study createStudy(String name, int count); // 스터디 생성 및 최상위 폴더 생성

    Study getStudyById(Long id); // id로 스터디 찾기


}
