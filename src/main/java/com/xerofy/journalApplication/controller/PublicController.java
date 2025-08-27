package com.xerofy.journalApplication.controller;

import com.xerofy.journalApplication.entity.User;
import com.xerofy.journalApplication.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/create-user")
    public void createById(@RequestBody User user){
        userServices.saveEntry(user);
    }
}
