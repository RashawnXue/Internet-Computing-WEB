package com.web.springboot.controller;

import com.web.springboot.entity.User_data;
import com.web.springboot.repository.User_dataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_data")
public class User_dataHandler {
    @Autowired
    private User_dataRepository user_dataRepository;

    @PostMapping("/save")
    public String Registered(@RequestBody User_data User_data) {
        User_data user_data = user_dataRepository.findByUserName(User_data.getUser_name());

        return "";
    }
}
