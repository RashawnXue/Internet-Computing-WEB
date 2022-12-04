package com.web.springboot.controller;

import com.web.springboot.repository.User_dataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_data")
public class User_dataHandler {
    @Autowired
    private User_dataRepository user_dataRepository;

}
