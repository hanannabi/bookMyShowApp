package com.example.bookmyshow.controller;

import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.dtos.UserRequest;
import com.example.bookmyshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody UserRequest userRequest){
       return userService.addUser(userRequest);

    }
}
