package com.hospital.hms.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {
    private Integer id;

    private String paramKey;

    private String paramValue;

    /**
     * 状态
     */
    private Boolean status;

    private String remark;

    private static final long serialVersionUID = 1L;
}