package com.hospital.hms.dao;

import com.hospital.hms.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Mapper
public interface DeptDao {
    ArrayList<HashMap> selectAll();
    ArrayList<HashMap> selectByCondition(HashMap condition);

    Long selectByConditionCount(HashMap condition);

    Integer insertDept(Dept dept);

    HashMap selectDeptById(Integer deptId);

    Integer updateDept(Dept dept);

    Integer deleteDeptById(Integer[] ids);
    boolean selectCanDelete(Integer[] ids);


}
