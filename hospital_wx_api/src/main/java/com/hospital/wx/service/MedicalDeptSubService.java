package com.hospital.wx.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface MedicalDeptSubService {
    ArrayList<HashMap> selectMedicalDeptSubList(int deptId);
}
