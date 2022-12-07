package com.web.springboot.controller;

import com.web.springboot.repository.CourseRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/resource")
public class ResourceHandler {
    @Autowired
    private CourseRepository courseRepository;


    @PostMapping("/uploadfile")
    public String uploadFile(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
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
