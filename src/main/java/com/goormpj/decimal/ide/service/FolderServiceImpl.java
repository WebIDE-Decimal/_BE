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
        Folder parentFolder = folderRepository.findById(parentId).orElse(null);

        int depth;
        if (parentFolder != null) {
            depth = parentFolder.getDepth() + 1;
        } else {
            depth = 0;
        }

        Folder newFolder = new Folder(folderName, parentFolder, depth);
        return folderRepository.save(newFolder);
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
            folderRepository.delete(folder);
        }

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
