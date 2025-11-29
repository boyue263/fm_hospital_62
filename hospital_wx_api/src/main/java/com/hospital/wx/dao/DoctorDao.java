package com.hospital.wx.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface DoctorDao {
    HashMap selectDoctorInfoById(int id);
}
