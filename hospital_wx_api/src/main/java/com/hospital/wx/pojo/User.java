package com.hospital.wx.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User：用户信息表
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;


    private String password;

    /**
     * 预留
     */
    private String openId;


    /**
     * 预留
     */
    private String photo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Object sex;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 入职日期
     */
    private Date hiredate;

    /**
     * 角色
     */
    private Object role;

    /**
     * 是否是超级管理员
     */
    private Boolean root;

    /**
     * 部门编号
     */
    private Integer deptId;

    /**
     * 状态
     * 1 离职
     * 2 在职
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

}