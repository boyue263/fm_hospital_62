package com.hospital.hms.sercive.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.hospital.common.exception.GlobalException;
import com.hospital.common.utils.PageUtils;
import com.hospital.hms.dao.DoctorDao;
import com.hospital.hms.dao.MedicalDeptSubDoctorDao;
import com.hospital.hms.pojo.Doctor;
import com.hospital.hms.pojo.MedicalDeptSubDoctor;
import com.hospital.hms.sercive.DoctorService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private MedicalDeptSubDoctorDao medicalDeptSubDoctorDao;
    @Override
    public PageUtils selectDoctorByPage(Map<String, Object> map) {
        ArrayList<Map<String,Object>> list = doctorDao.selectDoctorByPage(map);
        Long count =doctorDao.selectDoctorCount(map);
        Integer start = (Integer) map.get("start");
        Integer length = (Integer) map.get("length");
        PageUtils pageUtils = new PageUtils(list,count, start, length);
        return pageUtils;
    }

    @Override
    public HashMap<String, Object> selectDoctorDetailById(int id) {
        HashMap<String, Object> map = doctorDao.selectDoctorDetailById(id);
        map.replace("tag", JSONUtil.parseArray(MapUtil.getStr(map, "tag")));
        return map;
    }

    @Override
    @Transactional
    public void insert(Map param) {
        try {
            Doctor doctor = BeanUtil.toBean(param, Doctor.class);
            doctorDao.insert(doctor);

            Integer doctorId = doctor.getId();

            Integer subId = MapUtil.getInt(param, "subId");
            MedicalDeptSubDoctor subDoctor = new MedicalDeptSubDoctor();
            subDoctor.setDoctorId(doctorId);
            subDoctor.setDeptSubId(subId);
            medicalDeptSubDoctorDao.insert(subDoctor);
        } catch (Exception e) {
            log.error("Error occurred while inserting doctor and medicalDeptSubDoctor: ", e);
            // 抛出异常以触发事务回滚
            throw e;
        }
    }

    @Override
    @Transactional
    public HashMap<String, Object> selectById(int id) {
        HashMap<String, Object> map = doctorDao.selectById(id);
        try {
            if (map != null && map.containsKey("tag")) {
                String tag = (String) map.get("tag");
                if (tag != null && !tag.isEmpty()) {
                    JSONArray jsonArray = JSONUtil.parseArray(tag);
                    map.put("tag", jsonArray);
                }
            }
        } catch (Exception e) {
            log.error("Error parsing tag JSON: ", e);
        }
        return map;
    }

    @Override
    public void update(Map<String, Object> map) {
        try {
            doctorDao.update(map);
            Map<String,Object> updateMap = MapUtil.renameKey(map,"id","doctorId");
            medicalDeptSubDoctorDao.updateDoctorSubDept(updateMap);
        } catch (Exception e) {
            log.error("Error updating doctor and department information: ", e);
            throw new RuntimeException("Failed to update doctor and department information", e);
        }

    }

    @Override
    @Transactional
    public void deleteDoctorByIds(Integer[] ids) {
        try {
            // 删除医生集合
            doctorDao.deleteByIds(ids);
            // 删除该医生关联的诊室数据
            medicalDeptSubDoctorDao.deleteByDoctorId(ids);
        } catch (Exception e) {
            // 异常捕获并记录日志
            log.error("Error deleting doctors with ids: {}", ids, e);
            throw new RuntimeException("Failed to delete doctors and their related departments.", e);
        }
    }

    @Override
    public ArrayList<HashMap<String,Object>> selectDoctorBySubId(int subId) {
        return doctorDao.selectDoctorBySubId(subId);
    }
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.bucket-name}")
    private String bucketName;
    private static final String DOCTOR_FILE_PREFIX = "doctor-";  // 文件名前缀
    private static final String FILE_EXTENSION = ".jpg";  // 文件扩展名
    private static final int MAX_FILE_SIZE = 5 * 1024 * 1024;  // 最大文件上传大小，5MB
    @Override
    @Transactional
    public void updatePicture(MultipartFile file, Integer doctorId) {
        try {
            String filename = DOCTOR_FILE_PREFIX + doctorId + FILE_EXTENSION;
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endpoint)  // Minio服务器地址
                    .credentials(accessKey, secretKey)  // Minio认证的accessKey和secretKey
                    .build();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)  // 指定存储桶的名称
                            .object("doctor/" + filename)  // 设置存储路径和文件名
                            .stream(file.getInputStream(), -1, MAX_FILE_SIZE)  // 设置上传流、文件长度和最大文件大小，-1
                            .contentType("image/jpeg")  // 设置文件类型为JPEG图片
                            .build()
            );
            HashMap<String, Object> updateParams = new HashMap<>();
            updateParams.put("id", doctorId);  // 设置医生ID
//            updateParams.put("photo", "/doctor/" + filename);
//            updateParams.put("photo", "/hospital/doctor/" + filename);
            updateParams.put("photo", "/hospital/doctor/" + filename);
// 设置图片路径
            // 更新数据库中的图片路径信息
            doctorDao.updatePicture(updateParams);
            System.out.println("baseUrl=" + endpoint);

        } catch (Exception e) {
            log.error("照片更新失败",e);
            throw new GlobalException("照片更新失败");
        }

    }
}
