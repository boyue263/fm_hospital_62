package com.hospital.wx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MedicalDeptService {
    ArrayList<HashMap> selectMedicalDeptList(Map param);
}
