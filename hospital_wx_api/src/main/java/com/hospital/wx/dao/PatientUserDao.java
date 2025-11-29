package com.hospital.wx.dao;
import com.hospital.wx.pojo.PatientUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface PatientUserDao {

    int insert(PatientUser entity);
    Integer existsUserByOpenId(String openId);
    HashMap selectOpenIdByUserId(int userId);


}