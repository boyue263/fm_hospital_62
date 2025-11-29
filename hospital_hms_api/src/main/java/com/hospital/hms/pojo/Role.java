package com.hospital.hms.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class Role implements Serializable {
    private Integer id;

    private String roleName;

    private String permissions;

    private String desc;

    private String defaultPermissions;

    /*
    是否是系统内置角色：
        1. 是
        0. 否
    * */
    private Boolean systemic;

    private static final long serialVersionUID = 1L;
}