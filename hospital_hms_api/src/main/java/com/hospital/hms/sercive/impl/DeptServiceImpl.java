package com.hospital.hms.sercive.impl;

import com.hospital.common.exception.GlobalException;
import com.hospital.common.utils.PageUtils;
import com.hospital.hms.dao.DeptDao;
import com.hospital.hms.dao.UserDao;
import com.hospital.hms.pojo.Dept;
import com.hospital.hms.sercive.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private UserDao userDao;

    @Override
    public ArrayList<HashMap> selectAll() {
        return deptDao.selectAll();
    }

    @Override
    public Integer insertDept(Dept dept) {
        return deptDao.insertDept(dept);
    }

    @Override
    public PageUtils selectDeptByCondition(HashMap param) {
        ArrayList list = deptDao.selectByCondition(param);
        Long count = deptDao.selectByConditionCount(param);
        Integer start = (Integer) param.get("start");
        Integer length = (Integer) param.get("length");
        PageUtils pageUtils = new PageUtils(list,count,start,length);
        return pageUtils;

    }

    @Override
    public Integer updateDept(Dept dept) {
        return deptDao.updateDept(dept);
    }

    @Override
    public Integer deleteDeptById(Integer[] ids){
        if(!deptDao.selectCanDelete(ids)){
            throw new GlobalException("无法删除关联用户的部门");
        }
        int rows = deptDao.deleteDeptById(ids);
        return rows;
    }
    @Override
    public HashMap selectDeptById(Integer deptId) {
        return deptDao.selectDeptById(deptId);
    }
}
