package ru.univer.db.vcs.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.univer.db.vcs.dto.*;

import java.util.Map;

@Repository
public class CRUDRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Object userLogIn(UserDto user){
        String saveUser = "select user_registration (?,?)";
        Map res = jdbcTemplate.queryForMap(saveUser, user.getName(), user.getPassword());
        return res.get("user_registration");
    }

    public Object userSignIn(String userName, String userPassword){
        String userSignIn = " select user_sign_in (?,?)";
        Map user_signIn = jdbcTemplate.queryForMap(userSignIn, userName, userPassword);
        return user_signIn.get("user_sign_in");
    }

    /**
     * Save created repository in "Repositories" and add connection between user and repo in "Repo_Storage"
     */
    public void saveRepository(RepositoryDto repo, int user_id){
        String completeAddRepo = "select create_new_repo(?, ?)";
        jdbcTemplate.queryForMap(completeAddRepo, repo.getName(), user_id);
    }

    public void saveBranch(BranchDto branch){
        String insertBranch = "insert into \"Branch\" (name, repo_id) VALUES (?, ?)";
        String name = branch.getName().replaceAll("\\s", "-");
        jdbcTemplate.update(insertBranch, name, branch.getRepo_id());
    }

    public void saveEditedCodeFile(CodeFileDto codeFile){
        String saveCodeFile = "select edit_file(?, ?)";
        Object res = jdbcTemplate.queryForMap(saveCodeFile, codeFile.getContent(), codeFile.getId());
    }

}
