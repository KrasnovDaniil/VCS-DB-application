package ru.univer.db.vcs.repositories;

import ru.univer.db.vcs.dto.BranchDto;
import ru.univer.db.vcs.dto.CodeFolderDto;
import ru.univer.db.vcs.dto.RepositoryDto;
import ru.univer.db.vcs.dto.UserDto;

public class CRUDRepository {
    public void saveUser(UserDto user){
        // insert into "User" values (user.id, user.name, ...)
    }

    public void saveRepository(RepositoryDto repo){

    }

    public void saveBranch(BranchDto branch){

    }

    public void saveCodeFolder(CodeFolderDto codeFolder){

    }

    //  and the same with other DTOs
}
