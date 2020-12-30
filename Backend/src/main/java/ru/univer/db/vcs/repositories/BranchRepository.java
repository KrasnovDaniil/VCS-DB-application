package ru.univer.db.vcs.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BranchRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public Object findCodeContentOfBranch(int branch_id){
        String findCodeFolers = "select * from \"Code_folder\" CF where CF.branch = ?";
        Object folders = jdbcTemplate.queryForList(findCodeFolers, branch_id);
        return folders;
    }

    public Object getBranchNameById(int branch_id){
        String getName = "select \"Branch\".name from \"Branch\" where branch_id=?";
        Map<String, Object> name = jdbcTemplate.queryForMap(getName, branch_id);
        return name.get("name");
    }


    public Object getCodeFiles(int folder_id){
        String getFiles = "select * from \"Code_file\" Cf where Cf.folder_id = 2";
        Object files = jdbcTemplate.queryForList(getFiles);
        return files;
    }

    public Object getFolderNameById(int folder_id){
        String getName = "select \"Code_folder\".name from \"Code_folder\" where code_folder_id=?";
        Map<String, Object> name = jdbcTemplate.queryForMap(getName, folder_id);
        return name.get("name");
    }

    public Object getFileContent(int file_id){
        String getFileContent = "select code_text from \"Code_file\" where code_file_id=?";
        Map<String, Object> content = jdbcTemplate.queryForMap(getFileContent, file_id);
        return content.get("code_text");
    }

    public Object getFileNameById(int file_id){
        String getName = "select \"Code_file\".name from \"Code_file\" where code_file_id=?";
        Map<String, Object> name = jdbcTemplate.queryForMap(getName, file_id);
        return name.get("name");
    }
}
