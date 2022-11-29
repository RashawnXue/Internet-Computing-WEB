package com.web.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Courses {
    @Id
    private Integer id;
    private String course_name;
    private String teacher_name;
    private String study_time;
    private String introduction;
}
