package com.goormpj.decimal.ide.service;

import com.goormpj.decimal.ide.domain.Folder;
import com.goormpj.decimal.ide.repository.FolderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class FolderServiceImpl implements FolderService {

    private FolderRepository folderRepository;

    @Override
    public Folder createFolder(String folderName, Long parentId) {
        Folder parentFolder = null;
        int depth = 0;

        if (parentId != null) {
            parentFolder = folderRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("부모 폴더를 찾을 수 없습니다."));
            if (isDescendant(parentFolder, parentId)) {
                throw new IllegalArgumentException("부모 폴더가 현재 폴더의 하위 폴더를 가리킬 수 없습니다.");
            }
            depth = parentFolder.getDepth() + 1;
        } else {
            // parentId가 null이면서 부모 폴더가 없는 경우 최상위 폴더를 생성
            List<Folder> topLevelFolders = folderRepository.findByParentFolderIsNull();
            if (!topLevelFolders.isEmpty()) {
                throw new IllegalArgumentException("최상위 폴더는 하나만 생성할 수 있습니다.");
            }
        }

        Folder newFolder = new Folder(folderName, parentFolder, depth);
        folderRepository.save(newFolder);// 새로운 폴더 저장

        // 부모 폴더가 있고, 새로운 폴더가 생성되었다면 부모 폴더의 childFolders에 추가
        if (parentFolder != null) {
            parentFolder.getChildFolders().add(newFolder);
            folderRepository.save(parentFolder); // 부모 폴더 업데이트
        }

        return newFolder;
    }


    private boolean isDescendant(Folder folder, Long parentId) {
        if (folder == null || folder.getParentFolder() == null) {
            return false; // 현재 폴더가 null이거나 부모 폴더가 없는 경우 false 반환
        }
        if (folder.getId().equals(parentId)) {
            return true; // 현재 폴더의 id가 주어진 parentId와 같은 경우 true 반환
        }
        return isDescendant(folder.getParentFolder(), parentId); // 부모 폴더로 재귀 호출
    }

    @Override
    public Folder updateFolder(Long folderId, Long parentId) {
        Folder folder = folderRepository.findById(folderId).orElse(null);

        if (folder == null) {
            return null;
        }

        Folder parentFolder = folderRepository.findById(parentId).orElse(null);

        int depth;
        if (parentFolder != null) {
            depth = parentFolder.getDepth() + 1;
        } else {
            depth = 0;
        }

        folder.setParentFolder(parentFolder);
        folder.setDepth(depth);

        return folderRepository.save(folder);
    }

    @Override
    public void deleteFolder(Long folderId) {
        Folder folder = folderRepository.findById(folderId).orElse(null);
        if (folder != null) {
            deleteFolderRecursively(folder);
        }
    }

    private void deleteFolderRecursively(Folder folder) {
        List<Folder> childFolders = folder.getChildFolders();
        for (Folder childFolder : childFolders) {
            deleteFolderRecursively(childFolder);
        }
        folderRepository.delete(folder);
    }

    @Override
    public List<Folder> getAllFolders() {
        return folderRepository.findByParentFolderIsNull();
    }

    @Override
    public List<Folder> getChildFolders(Long parentId) {
        Folder parentFolder = folderRepository.findById(parentId).orElse(null);
        if (parentFolder == null) {
            return Collections.emptyList();
        }

        return parentFolder.getChildFolders();
    }
}
