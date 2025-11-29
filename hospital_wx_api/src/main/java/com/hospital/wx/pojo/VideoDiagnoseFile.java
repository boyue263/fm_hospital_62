package com.hospital.wx.pojo;

import lombok.Data;

@Data
public class VideoDiagnoseFile {
    private Integer id;
    private Integer videoDiagnoseId;
    private String filename;
    private String path;
    private String createTime;
}