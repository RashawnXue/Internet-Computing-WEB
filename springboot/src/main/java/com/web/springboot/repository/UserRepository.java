package com.web.springboot.repository;

import com.web.springboot.entity.User;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Nullable
    User findByusername(String userName);
}
