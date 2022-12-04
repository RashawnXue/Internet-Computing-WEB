package com.web.springboot.repository;

import com.web.springboot.entity.User_data;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_dataRepository extends JpaRepository<User_data, Integer> {
    User_data findByUserName(String userName);
}
