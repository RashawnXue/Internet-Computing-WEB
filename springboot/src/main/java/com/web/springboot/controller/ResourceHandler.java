package com.web.springboot.controller;

import com.web.springboot.entity.Resource;
import com.web.springboot.entity.ResourceData;
import com.web.springboot.repository.CourseRepository;
import com.web.springboot.repository.ResourceRepository;
import com.web.springboot.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.util.List;


@RestController
@RequestMapping("/resource")
public class ResourceHandler {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * //TODO 在本地跑后端，这里要改成自己文件夹
     */
    private String path_file = "/home/floveram/WEB/data/resource_data/";
    private final Logger logger =  LoggerFactory.getLogger(ResourceHandler.class);

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
    /**
     * 根据课程名字搜索该课程的资源
     * url:"/resource/{coursename}"
     *
     * @param coursename 该资源所属课程id
     * @return 该课程的所有资源列表
     */
    @GetMapping("/{coursename}")
    public List<Resource> findByCourseName(@PathVariable("coursename") String coursename) {
        return resourceRepository.findByCourseid(courseRepository.findByCoursenameLike(coursename).get(0).getId());
    }

    /**
     *
     * @param resourceData 传入参数：json对象 (包含
     *                     file:文件
     *                     coursename: 对应的课程名
     *                     username: 上传者的用户名
     *                     name: 资源的名称
     *                     intro: 资源的简介
     *                     )
     * @return 返回状态："empty_file":文件为空文件（可能传文件出了问题）
     *                 "exists":文件已存在（根据文件 的上传名字判断）*注意区分文件名和文件上传的名字*
     *                 "fail": 后端问题，上传失败（可以看看后端log，联系lds）
     *                 "success": 上传成功
     */
    @PostMapping("/uploadfile")
    public String uploadFile(@RequestBody ResourceData resourceData) {
        logger.info("进入上传方法");
        logger.warn("请 先到 springboot/controller/ResourceHandler.java  中 TODO位置更换到自己要存文件的路径（绝对路径）");
        logger.info("传入数据："+resourceData.toString());

        MultipartFile file = resourceData.getFile();
        if (file.isEmpty()) {
            logger.warn("\n !!! : 文件为空\n");
            return "empty_file";
        }
        if (resourceRepository.findByName(resourceData.getName()) != null){
            logger.warn("重复添加了文件  " + resourceData.getName());
            return "exists";
        }
        String fileName = file.getOriginalFilename();
        String typeName = fileName.substring(fileName.lastIndexOf("."));

        String filePath = path_file;
        File dest = null;
        try {
            dest = new File(filePath + fileName);
        } catch (NullPointerException e) {
            logger.warn("创建本地文件失败，文件路径错误，请到ResourceHandler中修改到自己的路径");
            return "fail";
        }
        Resource resource2save = new Resource();
        int courseID = courseRepository.findByCoursename(resourceData.getCoursename()).getId();
        int uploaderID = userRepository.findByUsername(resourceData.getUsername()).getId();
        {
            resource2save.setCourseid(courseID);
            resource2save.setType(typeName);
            resource2save.setSize((int) file.getSize());
            resource2save.setDatapath(filePath + fileName);
            resource2save.setUploaderid(uploaderID);
            resource2save.setName(resourceData.getName());
            resource2save.setIntro(resourceData.getIntro());
            logger.info("上传文件 " + resource2save.getName() + " 到 " + resource2save.getDatapath());
        }

        Resource res = resourceRepository.save(resource2save);
        if (res.getDatapath() == null) {
            logger.warn("数据库添加文件信息失败（可能）");
            return "fail";
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            logger.warn("文件写入失败");
            return "fail";
        }
        return "success";
    }

    /**
     * 实现文件下载
     * url:"/downloadfile/{resourceId}
     *
     * @param response
     * @param resourceId 资源对应的id
     * @return path error: 数据库中datapath为null
     *         not exist：文件不存在
     *         fail：文件下载失败
     *         success：下载成功
     */
    @RequestMapping("/downloadfile/{resourceId}")
    public String downloadFile(HttpServletResponse response, @PathVariable("resourceId") int resourceId){
        Resource target = resourceRepository.findById(resourceId);
        String url = target.getDatapath();
        if (url == null) {
            logger.error("无法读取文件路径");
            return "path error";
        }
        File file = new File(url);
        if (!file.exists()){
            logger.warn("文件不存在");
            return "not exist";
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + resourceId );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            logger.error("下载失败");
            return "fail";
        }
        return "success";
    }
}
