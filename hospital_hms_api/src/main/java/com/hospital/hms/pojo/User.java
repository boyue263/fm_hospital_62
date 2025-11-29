package com.hospital.hms.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String sex;
    private String tel;
    private String email;
    private Integer deptId;
    private String job;
    private Integer refId;
    private Byte status;
    private String createTime;

}