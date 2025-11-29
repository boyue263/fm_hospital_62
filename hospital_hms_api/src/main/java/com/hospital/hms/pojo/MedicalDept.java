package com.hospital.hms.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MedicalDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * */
    private Integer id;

    /**
     * 科室名称
     * */
    private String name;

    /**
     * 是否为门诊
     * */
    private Boolean outpatient;

    /**
     * 详细注释
     * */
    private String description;

    /**
     * 是否为优秀科室
     * */
    private Boolean recommended;
}