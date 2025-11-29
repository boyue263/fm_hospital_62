package com.hospital.hms.sercive;

import com.hospital.common.utils.PageUtils;
import com.hospital.hms.pojo.Dept;

import java.util.ArrayList;
import java.util.HashMap;


public interface DeptService {
    ArrayList<HashMap> selectAll();
    PageUtils selectDeptByCondition(HashMap param);
    Integer insertDept(Dept dept);

    Integer updateDept(Dept dept);

    Integer deleteDeptById(Integer[] ids);
    HashMap selectDeptById(Integer deptId);


}
