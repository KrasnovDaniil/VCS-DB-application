package ru.univer.db.vcs.dto;

import java.util.List;

public class BranchDto {
    private int id;
    private int repo_id;
    private String name;
    private List<CodeFolderDto> codeFolders;
    private List<CodeFileDto> codeFiles;

    public BranchDto() {
    }

    public int getRepo_id() {
        return repo_id;
    }

    public void setRepo_id(int repo_id) {
        this.repo_id = repo_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CodeFolderDto> getCodeFolders() {
        return codeFolders;
    }

    public void setCodeFolders(List<CodeFolderDto> codeFolders) {
        this.codeFolders = codeFolders;
    }

    public List<CodeFileDto> getCodeFiles() {
        return codeFiles;
    }

    public void setCodeFiles(List<CodeFileDto> codeFiles) {
        this.codeFiles = codeFiles;
    }
}
