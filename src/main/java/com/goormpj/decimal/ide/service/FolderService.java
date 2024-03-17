package com.goormpj.decimal.ide.service;

import com.goormpj.decimal.ide.domain.Folder;
import com.goormpj.decimal.ide.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FolderService {

    Folder createFolder(String folderName, Long parentId); // 폴더 생성

    Folder updateFolder(Long folderId, Long parentId); // 폴더 수정

    void deleteFolder(Long folderId); // 폴더 삭제

    List<Folder> getAllFolders(); // 상위 폴더 찾기

    List<Folder> getChildFolders(Long parentId); // 하위 폴더 찾기

}
