package com.hospital.wx.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mapper
public interface MedicalDeptDao {
    ArrayList<HashMap> selectMedicalDeptList(Map param);
}
