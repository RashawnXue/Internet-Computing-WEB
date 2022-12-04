package com.web.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 课程资源实体类
 */
@Entity
@Data
public class Resource_table {
    @Id
    private Integer id;
    private String name;
    private String type;
    private String data_path;
    private Integer uploader_id;
    private Integer size;
    private Integer course_id;
}
