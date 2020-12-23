package ru.univer.db.vcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.univer.db.vcs.repositories.UserRepository;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @GetMapping("/get_repos")
    public void getRepos(){
//        return list of repos of that user

    }



}
