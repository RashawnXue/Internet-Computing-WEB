package com.web.springboot.repository;

import com.web.springboot.entity.Resource;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource,Integer> {
    @Nullable
    List<Resource> findByCourseid(Integer courseid);
    Resource findByName(String name);

    Resource findById(int id);
}
