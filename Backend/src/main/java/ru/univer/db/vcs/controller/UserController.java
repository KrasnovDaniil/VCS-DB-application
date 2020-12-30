package ru.univer.db.vcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.univer.db.vcs.dto.RepositoryDto;
import ru.univer.db.vcs.dto.UserDto;
import ru.univer.db.vcs.repositories.BranchRepository;
import ru.univer.db.vcs.repositories.CRUDRepository;
import ru.univer.db.vcs.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {


    private UserRepository userRepo;
    private CRUDRepository crudRepo;
    private BranchRepository branchRepo;


    @Autowired
    public UserController(UserRepository userRepo, CRUDRepository crudRepo, BranchRepository br){
        this.userRepo = userRepo;
        this.crudRepo = crudRepo;
        branchRepo = br;
    }

    @GetMapping("login")
    public String loginPage(){
        return "loginMenu";
    }

    @GetMapping("signIn")
    public String signInPage(){
        return "signInMenu";
    }

    @PostMapping("signIn")
    public String login(@RequestParam(name = "Name") String userName,
                        @RequestParam(name = "Password") String userPassword){

        Object userId = crudRepo.userSignIn(userName, userPassword);
        return "redirect:/v1/user/"+userId+"/get_repos";
    }

    @PostMapping("logIn")
    public String registration(@RequestParam(name = "Name") String userName,
                               @RequestParam(name = "Password") String userPassword){
        UserDto userDto = new UserDto();
        userDto.setName(userName);
        userDto.setPassword(userPassword);
        Object userId = crudRepo.userLogIn(userDto);
        return "redirect:/v1/user/"+userId+"/get_repos";
    }

    @GetMapping("v1/user/{user_id}/test")
    public String test(Model model){
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto(1,"Daniil", "dan@mail.ru","123",1,2));
        users.add(new UserDto(2,"ASDAS", "ASD@mail.ru","321",2,1));
        model.addAttribute("users", users);
        return "users";
    }


    @GetMapping("v1/user/{user_id}/get_repos")
    public String getRepos(@PathVariable(name = "user_id") int user_id, Model model){
        Object res = userRepo.getAllRepos(user_id);
        model.addAttribute("repos", res);
        return "userAccount";
    }


    @GetMapping(value = "v1/user/{user_id}/rep/{repo_name}")
    public String showCurrentRepo(@PathVariable(name = "user_id") int user_id,
                                  @PathVariable(name = "repo_name") String repo_name,
                                  Model model){
        RepositoryDto res = userRepo.findRepo(user_id, repo_name);
        model.addAttribute("r_name", res.getName());
        model.addAttribute("stars", res.getStars());
        model.addAttribute("branches", res.getBranches());
        // ищу по имени репозитория данные о нём и добавляю их к модели и вывожу
        Object branches = userRepo.getAllBranches(res.getId());
        model.addAttribute("BranchList", branches);
        return "repo";
    }


    @PostMapping(value = "v1/user/{user_id}/createNewRepo")
    public String createRepo(@RequestParam(name = "repoName") String repoName,
                             @PathVariable(name = "user_id") int user_id){
        System.out.println("model:");
        repoName = repoName.replaceAll("\\s","-");
        RepositoryDto repoDto = new RepositoryDto();
        repoDto.setName(repoName);
        crudRepo.saveRepository(repoDto, user_id);
        return "redirect:/v1/user/{user_id}/get_repos";
    }


}

