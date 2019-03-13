package com.mycompanion.mycompanion.controller;

import com.mycompanion.mycompanion.entity.User;
import com.mycompanion.mycompanion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService uService){
        userService = uService;
    }

    @RequestMapping(path = "/register")
    public void register(@RequestBody User user){
        userService.create(user);
    }
}
