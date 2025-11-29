package com.hospital.hms.sercive;

import com.hospital.common.utils.PageUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public interface DoctorService {
    PageUtils selectDoctorByPage(Map<String,Object> map);
    HashMap<String,Object> selectDoctorDetailById(int id);
    void insert(Map<String,Object> param);
    HashMap<String,Object> selectById(int id);
    void update(Map<String,Object> map);
    void deleteDoctorByIds(Integer[] ids);
    ArrayList<HashMap<String,Object>> selectDoctorBySubId(int subId);
    void updatePicture(MultipartFile file, Integer doctorId);
}
