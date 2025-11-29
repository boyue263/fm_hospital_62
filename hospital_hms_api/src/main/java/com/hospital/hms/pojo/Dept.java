package com.hospital.hms.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String deptName;

    private String tel;

    private String email;

    private String desc;
}