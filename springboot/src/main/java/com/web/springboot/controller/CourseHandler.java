package com.web.springboot.controller;

import com.web.springboot.entity.Courses;
import com.web.springboot.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseHandler {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/findAll")
    public List<Courses> findAll() {
        return courseRepository.findAll();
    }
}
