package ru.univer.db.vcs.repositories;

import org.springframework.stereotype.Repository;
import ru.univer.db.vcs.dto.*;

import java.util.List;

@Repository
public class UserRepository {

    public void saveUser(UserDto user){
        // insert into "User" values (id, user.name, user.email, ...);
    }

    public List<RepositoryDto> getAllRepos(){
        // execute query from VCS DB and cast response to list of repositories
        return null;
    }

    public List<HistoryItemDto> getHistory(int currId){
        // select history_item from History_items where user.id = currId
        return null;
    }

    // for Repositories

    public List<PrDto> getAllPRs(int repo_id){
        return null;
    }

    public List<BranchDto> getAllBranches(int repo_id){
        return null;
    }

    public List<Integer> countCommitsAndStars(int repo_id){
        return null;
    }

    // for PRs

    public List<CommitDto> getPRCommits(int pr_id){
        return null;
    }

    public List<BranchDto> getPRBranches(int pr_id){
        return null;
    }

    // for Branches
    public List<CommitDto> getAllCommits(int branch_id){
        return null;
    }

    public List<CodeFolderDto> getBranchFolders(int branch_id){
        return null;
    }

    public List<CodeFileDto> getBranchFiles(int branch_id){
        return null;
    }

    // for Code Folders

    public List<CodeFolderDto> getInnerFolders(int folder_id){
        return null;
    }

    public List<CodeFileDto> getInnerFiles(int folder_id){
        return null;
    }

    // for Code Files

    public List<String> getText(int file_id){
        return null;
    }

}
