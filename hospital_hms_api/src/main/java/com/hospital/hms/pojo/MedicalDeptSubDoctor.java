package com.hospital.hms.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MedicalDeptSubDoctor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer deptSubId;

    private Integer doctorId;
}