package com.web.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 用户实体类
 */
@Entity
@Data
public class User_data {
    @Id
    private Integer id;
    private String user_name;
    private String password;
    private String picture;
    private Integer contribution;
}
