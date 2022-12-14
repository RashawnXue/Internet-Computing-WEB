package com.web.springboot.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ResourceData extends Resource{
    private MultipartFile file;
    private String coursename;
    private String username;
    public ResourceData(MultipartFile file1,String coursename1,String username1,String name2,String intro){
        file = file1;
        coursename = coursename1;
        username = username1;
        setName(name2);
        setIntro(intro);
    }
}
