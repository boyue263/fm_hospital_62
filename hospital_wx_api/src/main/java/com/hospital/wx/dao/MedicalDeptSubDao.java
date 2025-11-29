package com.hospital.wx.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface MedicalDeptSubDao {

    ArrayList<HashMap> selectMedicalDeptSubList(int deptId);

}
