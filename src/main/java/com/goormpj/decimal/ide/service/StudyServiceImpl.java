package com.goormpj.decimal.ide.service;

import com.goormpj.decimal.ide.domain.Study;
import com.goormpj.decimal.ide.domain.Folder;
import com.goormpj.decimal.ide.repository.FolderRepository;
import com.goormpj.decimal.ide.repository.StudyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final FolderService folderService;

    // 스터디 생성 및 최상위 폴더 생성
    @Override
    public Study createStudy(String name, int count) {
        // 스터디 생성
        Study newStudy = new Study();
        newStudy.setName(name);
        newStudy.setMemberCount(count);

        // 스터디 저장
        studyRepository.save(newStudy);

        // 최상위 폴더 생성 및 스터디명으로 설정
        folderService.createRootFolderForStudy(newStudy);

        return newStudy;
    }

    // 스터디 ID로 스터디를 조회하는 메서드
    @Override
    public Study getStudyById(Long id) {
        Optional<Study> studyOptional = studyRepository.findById(id);
        return studyOptional.orElse(null);
    }

    // 스터디 수정
    public Study updateStudy(Long studyId, String name, int count) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new EntityNotFoundException("Study not found"));
        study.setName(name);
        study.setMemberCount(count);
        return studyRepository.save(study);
    }

    // 스터디 삭제
    public void deleteStudy(Long studyId) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new EntityNotFoundException("Study not found"));
        studyRepository.delete(study);
    }

    // 스터디 참여 멤버 추가
    public void addMember(Long studyId, Long memberId) {
        // 멤버 추가 로직 구현
    }

    // 스터디 참여 멤버 삭제
    public void removeMember(Long studyId, Long memberId) {
        // 멤버 삭제 로직 구현
    }

    // 스터디 문제 추가
    public void addProblem(Long studyId, Long problemId) {
        // 문제 추가 로직 구현
    }

    // 스터디 문제 삭제
    public void removeProblem(Long studyId, Long problemId) {
        // 문제 삭제 로직 구현
    }

    // 스터디 폴더 생성
    public void createFolder(Long studyId, String folderName) {
        // 폴더 생성 로직 구현
    }

    // 스터디 폴더 수정
    public void updateFolder(Long studyId, Long folderId, String folderName) {
        // 폴더 수정 로직 구현
    }

    // 스터디 폴더 삭제
    public void deleteFolder(Long studyId, Long folderId) {
        // 폴더 삭제 로직 구현
    }

    // 스터디 IDE 할당
    public void assignIDE(Long studyId, Long ideTableId) {
        // IDE 할당 로직 구현
    }

    // 기타 필요한 로직 추가
}

