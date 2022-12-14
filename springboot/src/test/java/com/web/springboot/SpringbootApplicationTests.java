package com.web.springboot;

import com.web.springboot.controller.UserHandler;
import com.web.springboot.entity.User;
import com.web.springboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserHandler userHandler;

    @Test
    void Register_test() {
        User user = new User();
        user.setUsername("xhh");
        user.setPassword("12345");
        user.setContribution(1);
        String res = userHandler.Register(user);
        System.out.println(res);
    }

    @Test
    void Login_test_success() {
        User user = new User();
        user.setUsername("李东升");
        user.setPassword("lds555");
        String res = userHandler.Login(user);
        System.out.println(res);
    }

    @Test
    void Login_test_fail() {
        User user = new User();
        user.setUsername("李东升");
        user.setPassword("lds5555");
        String res = userHandler.Login(user);
        System.out.println(res);
    }

    @Test
    void Login_test_notexist() {
        User user = new User();
        user.setUsername("xxjj");
        user.setPassword("lds5555");
        String res = userHandler.Login(user);
        System.out.println(res);
    }

    @Test
    void Sort() {
        List<UserHandler.rank_user> users = userHandler.Rank();
        for (UserHandler.rank_user u :
                users) {
            System.out.println(u.getUsername()+" "+u.getRank());
        }
    }
}
