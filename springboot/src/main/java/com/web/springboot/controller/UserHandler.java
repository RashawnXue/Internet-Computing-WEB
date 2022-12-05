package com.web.springboot.controller;

import com.web.springboot.entity.User;
import com.web.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")

    public String Register(@RequestBody User user_data1) {
        User user_data = userRepository.findByusername(user_data1.getUsername());
        if (user_data != null) {
            return "exist";
        }
        User receive1 = userRepository.save(user_data1);
        return "success";
    }

    @PostMapping("/login")
    public String Login(@RequestBody User user_data_receive) {
        User user_data = userRepository.findByusername(user_data_receive.getUsername());
        if (user_data == null) {
            return "not exist";
        } else {
            boolean find = user_data_receive.getPassword().equals(user_data.getPassword());
            if (find) {
                return "success";
            } else {
                return "fail";
            }
        }
    }
}
