package ru.univer.db.vcs.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.univer.db.vcs.dto.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private
    JdbcTemplate jdbcTemplate;


    public Object getAllRepos(int user_id){
        // execute query from VCS DB and cast response to list of repositories
        String getAllRepos = "select * from \"User\" join \"Repo_Storage\" RS on \"User\".user_id = RS.user_user_id\n" +
                "    join \"Repository\" R on RS.repo_id = R.repository_id where user_id=?";
        Object res = jdbcTemplate.queryForList(getAllRepos, user_id);
        return res;
    }

    public RepositoryDto findRepo(int user_id, String repo_name){
        String findRepo = "select R.repository_id, R.stars, R.branches, R.name from \"User\" join \"Repo_Storage\" RS on \"User\".user_id = RS.user_user_id\n" +
                "    join \"Repository\" R on RS.repo_id = R.repository_id where user_id=? and R.name = ?";

        Map<String, Object> result = jdbcTemplate.queryForMap(findRepo, user_id, repo_name);

        RepositoryDto res = jdbcTemplate.queryForObject(findRepo, new Object[]{user_id, repo_name}, new RowMapper<RepositoryDto>() {
            @Override
            public RepositoryDto mapRow(ResultSet rs, int i) throws SQLException {
                RepositoryDto repo = new RepositoryDto();
                repo.setId(rs.getInt(1));
                repo.setStars(rs.getInt(2));
                repo.setBranches(rs.getInt(3));
                repo.setName(rs.getString(4));
                return repo;
            }
        });


        return res;
    }

    public List<HistoryItemDto> getHistory(int currId){
        // select history_item from History_items where user.id = currId
        return null;
    }

    // for Repositories

    public List<PrDto> getAllPRs(int repo_id){
        return null;
    }

    public Object getAllBranches(int repo_id){
        String getBranches = "select B.branch_id, B.name from \"Branch\" B where repo_id = ?";
        Object branches = jdbcTemplate.queryForList(getBranches, repo_id);
        return branches;
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
