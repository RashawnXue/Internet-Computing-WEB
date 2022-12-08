package com.web.springboot.controller;

import com.web.springboot.entity.User;
import com.web.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserRepository userRepository;


    /**
     * 用户注册
     * url:"/user/register"
     *
     * @param user_data1 : 内为username 和 password两个字段
     * @return 字符串String
     * 用户已存在 返回 “exist”
     * 注册成功 返回 "success"
     */
    @PostMapping("/register")
    public String Register(@RequestBody User user_data1) {
//        try{
//            BufferedWriter out = new BufferedWriter(new FileWriter("1.txt"));
//            out.write();
//            out.close();
//        }catch(IOException e){
//
//        }
        System.out.println(user_data1.getUsername() + user_data1.getPassword());
        User user_data = userRepository.findByusername(user_data1.getUsername());
        if (user_data != null) {
            return "exist";
        }



        User receive1 = userRepository.save(user_data1);
        return "success";
    }

    /**
     * 用户登录
     * url:"/user/login"
     *
     * @param user_data_receive : 内为username 和 password两个字段
     * @return 字符串String
     * 用户不存在 返回 “not exist”
     * 登录成功 返回 “success”
     * 密码不对 返回 “fail”
     */
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

    /**
     * 用户贡献度排行榜获取
     * URL="/user/rank"
     *
     * @return  返回一个列表 ，按照贡献度降序，存贮每个用户的信息，可自行取出username和contribution字段
     */
    @GetMapping("/rank")
    public List<User> Rank() {
        return userRepository.findByUsernameLikeOrderByContributionDesc("%");
    }
}
