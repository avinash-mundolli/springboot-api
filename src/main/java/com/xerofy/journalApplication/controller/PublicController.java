package com.xerofy.journalApplication.controller;

import com.xerofy.journalApplication.entity.User;
import com.xerofy.journalApplication.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/create-user")
    public void createById(@RequestBody User user){
        userServices.saveNewUser(user);
    }
    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";
    }
}
