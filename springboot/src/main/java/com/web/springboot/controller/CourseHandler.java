package com.web.springboot.controller;

import com.web.springboot.entity.Course;
import com.web.springboot.repository.CourseRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseHandler {
    @Autowired
    private CourseRepository courseRepository;

    /**
     * 访问所有课程信息
     * 并返回一个列表
     * 将此接口映射为url
     *
     * @return 装有所有课程信息的列表
     */
    @GetMapping("/findAll")
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    /**
     * 对课程进行弱模糊搜索
     * 并返回一个列表
     * 搜索模式：匹配关键字{name}，关键字必须完整
     * url:/findByName/{name}
     *
     * @return 匹配到的课程列表
     */
    @GetMapping("/findByName/{name}")
    public List<Course> findByCoursename(@PathVariable("name") String name){
        return courseRepository.findByCoursenameLike("%"+name+"%");
    }
}
