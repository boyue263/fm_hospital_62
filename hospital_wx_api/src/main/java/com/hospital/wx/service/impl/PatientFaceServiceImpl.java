package com.hospital.wx.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.hospital.wx.dao.PatientFaceDao;
import com.hospital.wx.dao.PatientUserInfoDao;
import com.hospital.wx.exception.GlobalException;
import com.hospital.wx.service.PatientFaceService;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.iai.v20180301.IaiClient;
import com.tencentcloudapi.iai.v20180301.models.CreatePersonRequest;
import com.tencentcloudapi.iai.v20180301.models.CreatePersonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Slf4j
public class PatientFaceServiceImpl implements PatientFaceService {
    @Value("${tencent.cloud.secretId}")
    private String secretId;

    @Value("${tencent.cloud.secretKey}")
    private String secretKey;

    @Value("${tencent.cloud.face.groupName}")
    private String groupName;

    @Value("${tencent.cloud.face.region}")
    private String region;

    @Autowired
    private PatientFaceDao patientFaceDao;

    @Autowired
    private PatientUserInfoDao patientUserInfoDao;
    @Override
    @Transactional
    public void createFaceModel(Map param) {
        // 从参数中获取用户ID和照片
        int userId = MapUtil.getInt(param, "userId");
        String photo = MapUtil.getStr(param, "photo");

        // 创建腾讯云身份认证凭证
        Credential cred = new Credential(secretId, secretKey);
        IaiClient client = new IaiClient(cred, region);

        // 查询用户姓名和性别
        Map<String, String> userInfo = patientUserInfoDao.selectPatientUserInfoByUserId(userId);
        String name = userInfo.get("name");
        String sex = userInfo.get("sex");

        // 创建人员请求对象
        CreatePersonRequest req = new CreatePersonRequest();
        // 设置人员所属的分组ID，将该人员添加到指定的人员库（groupName）中
        // 以后可以通过这个分组来查询、管理该人员的人脸数据
        req.setGroupId(groupName);

        // 将用户ID转换为字符串格式并设置为该人员的唯一ID
        // 该ID用于唯一标识每个用户，并在以后的人脸比对或查询中使用
        req.setPersonId(String.valueOf(userId));

        // 根据用户的性别（sex）来设置该人员的性别
        // 性别用数字表示，1代表男性，2代表女性
        req.setGender(getGender(sex));

        // 设置图像质量控制参数，值的范围是 0-4，
        // 其中 0 为最低质量，4 为最高质量。这里设置为 4，表示要求较高的图像质量
        req.setQualityControl(4L);

        // 设置唯一性控制参数，用来控制人脸与其他人员的相似度
        // 值的范围是 0-4，0表示最低，4表示最严格。设置为4，要求较高的唯一性
        req.setUniquePersonControl(4L);

        // 设置人员的姓名，这里将数据库中获取的用户姓名设置到该字段
        // 用于标识该人员的名字
        req.setPersonName(name);

        // 设置人员的照片，用于创建人脸模型。照片数据通常是经过Base64编码后的图片数据
        req.setImage(photo);

        try {
            // 发起创建人员请求，调用腾讯云的人脸识别服务来创建该人员的人脸模型
            // 传递的参数是之前设置的 CreatePersonRequest（req），包含了用户的基本信息和照片
            CreatePersonResponse resp = client.CreatePerson(req);

            // 判断腾讯云返回的响应中是否有有效的人脸ID
            // 如果 FaceId 不为空，说明人脸模型成功创建，响应中返回了该人员的人脸ID
            if (StrUtil.isNotBlank(resp.getFaceId())) {
                // 更新数据库，标记用户已录入人脸模型
                updateUserFaceModelStatus(userId);
            }
        } catch (TencentCloudSDKException e) {
            // 捕获腾讯云SDK异常并抛出全局异常
            throw new GlobalException(e);
        }
    }
    private long getGender(String sex) {
        return "男".equals(sex) ? 1L : 2L;
    }
    private void updateUserFaceModelStatus(int userId) {
        Map<String, Object> updateParams = Map.of("userId", userId, "existFaceModel", true);
        patientUserInfoDao.updateExistFaceModel(updateParams);
    }


}
