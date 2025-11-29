package com.hospital.wx.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Doctor：医生信息表
 */
@Data
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * */
    private Integer id;

    /**
     * 姓名
     * */
    private String name;

    /**
     * 身份证ID
     * */
    private String pid;

    /**
     * 工牌号
     */
    private String uuid;

    /**
     * 性别
     */
    private String sex;

    /**
     * 照片存储地址
     */
    private String photo;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 毕业院校
     */
    private String school;

    /**
     * 学位
     */
    private String degree;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 职位
     */
    private String job;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 详细介绍
     */
    private String description;

    /**
     * 入职日期
     */
    private Date hiredate;

    /**
     * 特长
     */
    private String tag;

    /**
     * 是否是优秀医生
     */
    private Boolean recommended;

    /**
     * 状态
     * 1在职，2离职，3退休，4隐藏（逻辑删除）
     */
    private Integer status;


    /**
     * 创建时间
     * 在数据库中自动生成
     */
    private Date create_time;

}