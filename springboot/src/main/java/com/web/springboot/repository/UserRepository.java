package com.web.springboot.repository;

import com.web.springboot.entity.User;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Nullable
    User findByusername(String userName);

    List<User> findByUsernameLikeOrderByContributionDesc(String name);

}
