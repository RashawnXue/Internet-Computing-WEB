package com.web.springboot.repository;

import com.web.springboot.entity.User_data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_dataRepository extends JpaRepository<User_data, Integer> {
}
