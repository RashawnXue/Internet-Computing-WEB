package com.web.springboot.controller;

import com.web.springboot.entity.Resource;
import com.web.springboot.repository.CourseRepository;
import com.web.springboot.repository.ResourceRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/resource")
public class ResourceHandler {
    @Autowired
    private CourseRepository courseRepository;

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

    @PostMapping("/uploadfile")
    public String uploadFile(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        Logger logger = (Logger) LoggerFactory.getLogger(ResourceHandler.class);
        if (file.isEmpty()){
            logger.info("\n !!! : 文件为空\n");
            return "empty_file";
        }

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String typeName = fileName.substring(fileName.lastIndexOf("."));
            String filePath = "/home/web/file";
            File dest = new File(filePath + fileName);
            file.transferTo(dest);
        }
        return "success";
    }

}
