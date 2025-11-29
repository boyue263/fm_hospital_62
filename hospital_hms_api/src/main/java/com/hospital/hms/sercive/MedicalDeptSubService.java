package com.hospital.hms.sercive;

import com.hospital.common.utils.PageUtils;
import com.hospital.hms.pojo.MedicalDept;
import com.hospital.hms.pojo.MedicalDeptSub;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.ArrayList;
import java.util.HashMap;

public interface MedicalDeptSubService {
    PageUtils selectMedicalDeptSubConditionByPage(HashMap<String,Object> map);
    Integer insertMedicalDeptSub(MedicalDeptSub medicalDeptSub);

    HashMap<String,Object> selectMedicalDeptSubById(Integer id);

    Integer updateMedicalDeptSub(MedicalDeptSub medicalDeptSub);
}
