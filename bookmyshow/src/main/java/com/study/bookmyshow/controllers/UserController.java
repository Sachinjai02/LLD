package com.study.bookmyshow.controllers;

import com.study.bookmyshow.models.User;
import com.study.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }
    public User createUser(String userName, String emailId) {
        return userService.createUser(userName, emailId);
    }
}
