package ru.univer.db.vcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.univer.db.vcs.dto.BranchDto;
import ru.univer.db.vcs.dto.RepositoryDto;
import ru.univer.db.vcs.repositories.BranchRepository;
import ru.univer.db.vcs.repositories.CRUDRepository;
import ru.univer.db.vcs.repositories.UserRepository;


@Controller
@RequestMapping("")
public class RepositoryController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BranchRepository branchRepo;
    @Autowired
    private CRUDRepository crudRepo;

    @GetMapping()
    public void test(){
        System.out.println();
    }

    @GetMapping(value = "v1/user/{user_id}/rep/{repo_name}/br/{branch_id}")
    public String gotoBranch(@PathVariable(name = "branch_id") int branch_id, Model model){
        Object branchFolders = branchRepo.findCodeContentOfBranch(branch_id);
        Object br_name = branchRepo.getBranchNameById(branch_id);
        model.addAttribute("name", br_name);
        model.addAttribute("FolderList", branchFolders);
        return "branch";
    }

    @PostMapping(value = "v1/user/{user_id}/rep/{repo_name}/createNewBranch")
    public String createNewBranch(@RequestParam(name = "branchName") String branchName,
                                  @PathVariable(name = "repo_name") String repoName,
                                  @PathVariable(name = "user_id") int userId){
        int cur_repo_id = userRepo.findRepo(userId, repoName).getId();
        BranchDto newBranch = new BranchDto();
        newBranch.setName(branchName);
        newBranch.setRepo_id(cur_repo_id);
        crudRepo.saveBranch(newBranch);
        return "redirect:/v1/user/{user_id}/rep/{repo_name}";
    }


}
