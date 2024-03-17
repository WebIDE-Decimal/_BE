package com.goormpj.decimal.ide.controller;

import com.goormpj.decimal.ide.domain.Folder;
import com.goormpj.decimal.ide.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ide")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @PostMapping
    public ResponseEntity<Folder> createFolder(@RequestParam String folderName, @RequestParam Long parentId) {
        Folder newFolder = folderService.createFolder(folderName, parentId);

        if (newFolder != null) {
            return new ResponseEntity<>(newFolder, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{folderId}")
    public ResponseEntity<Folder> updateFolder(@PathVariable Long folderId, @RequestParam Long parentId) {
        Folder updatedFolder = folderService.updateFolder(folderId, parentId);

        if (updatedFolder != null) {
            return new ResponseEntity<>(updatedFolder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long folderId) {
        folderService.deleteFolder(folderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Folder>> getAllFolders() {
        List<Folder> folders = folderService.getAllFolders();
        return new ResponseEntity<>(folders, HttpStatus.OK);
    }

    @GetMapping("/{parentId}/childFolders")
    public ResponseEntity<List<Folder>> getChildFolders(@PathVariable Long parentId) {
        List<Folder> subFolders = folderService.getChildFolders(parentId);
        return new ResponseEntity<>(subFolders, HttpStatus.OK);
    }
}
