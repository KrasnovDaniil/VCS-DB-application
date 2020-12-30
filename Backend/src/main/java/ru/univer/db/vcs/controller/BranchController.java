package ru.univer.db.vcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.univer.db.vcs.dto.CodeFileDto;
import ru.univer.db.vcs.repositories.BranchRepository;
import ru.univer.db.vcs.repositories.CRUDRepository;


@Controller
@RequestMapping("")
public class BranchController {

    @Autowired
    CRUDRepository crudRepository;
    @Autowired
    BranchRepository branchRepository;

    Object content = null;
    Object fileName = null;

    @GetMapping("v1/user/{user_id}/rep/{repo_name}/br/{branch_id}/folder/{codeFolder_id}")
    public String getCodeFiles(@PathVariable(name = "codeFolder_id") int codeFolder_id, Model model){
        Object fileList = branchRepository.getCodeFiles(codeFolder_id);
        Object folderName = branchRepository.getFolderNameById(codeFolder_id);
        model.addAttribute("FileList", fileList);
        model.addAttribute("f_name", folderName);
        return "codeFolder";
    }

    @GetMapping("v1/user/{user_id}/rep/{repo_name}/br/{branch_id}/folder/{codeFolder_id}/file/{code_file_id}")
    public String getCodeFileContent(@PathVariable(name = "code_file_id") int code_file_id, Model model){
        content = branchRepository.getFileContent(code_file_id);
        fileName = branchRepository.getFileNameById(code_file_id);
//        String con = ((String) content).replaceAll("/\n/g", "<br/>");
        model.addAttribute("Content", content);
        model.addAttribute("file_name", fileName);
        return "codeFile";
    }

    @GetMapping("v1/user/{user_id}/rep/{repo_name}/br/{branch_id}/folder/{codeFolder_id}/file/{code_file_id}/edit")
    public String editingCode(Model model){
        model.addAttribute("Content", content);
        model.addAttribute("file_name", fileName);
        return "editingCode";
    }

    @PostMapping("v1/user/{user_id}/rep/{repo_name}/br/{branch_id}/folder/{codeFolder_id}/file/{code_file_id}/edit")
    public String editingCodeFinished(@RequestParam( name = "editedContent") Object editedContent,
                                      @PathVariable(name = "code_file_id") int file_id){
        CodeFileDto file = new CodeFileDto();
        file.setName(fileName+"");
        file.setContent(editedContent+"");
        System.out.println(editedContent);
        file.setId(file_id);
        crudRepository.saveEditedCodeFile(file);
        return "redirect:/v1/user/{user_id}/rep/{repo_name}/br/{branch_id}/folder/{codeFolder_id}/file/{code_file_id}/";
    }


}
