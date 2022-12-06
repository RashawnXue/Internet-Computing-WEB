package com.web.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceHandlerTest {
    @Autowired
    private ResourceHandler resourceHandler = new ResourceHandler();

    @Test
    void findByCourseId(){
        System.out.println(resourceHandler.findByCourseId(1));
    }

}