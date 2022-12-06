package com.web.springboot.controller;

import com.web.springboot.entity.Resource;
import com.web.springboot.repository.CourseRepository;
import com.web.springboot.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceHandler {
    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * 根据课程id搜索该课程的资源
     * url:"/resource/{courseId}"
     *
     * @param courseId 该资源所属课程id
     * @return 该课程的所有资源列表
     */
    @GetMapping("/{courseId}")
    public List<Resource> findByCourseId(@PathVariable("courseId") Integer courseId) {
        return resourceRepository.findByCourseid(courseId);
    }
}
