package com.hospital.wx.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PatientFaceDao {
    Integer checkFaceAuthRecordForToday(Map param);
}
