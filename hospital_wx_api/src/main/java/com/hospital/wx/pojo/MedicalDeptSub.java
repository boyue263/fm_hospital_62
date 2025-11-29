package com.hospital.wx.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MedicalDeptSub implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer deptId;
    private String location;
}