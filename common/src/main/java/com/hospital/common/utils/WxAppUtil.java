package com.hospital.common.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hospital.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component("WxAppUtil")
public class WxAppUtil {
    @Value("${wechat.app-id}")
    private String appId;

    @Value("${wechat.app-secret}")
    private String appSecret;

    // 实现微信登录验证的接口
    private static final String URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String GRANT_TYPE = "authorization_code";

    public  String getOpenId(String code) {
        Map params = new HashMap<>();
        params.put("appid", appId);
        params.put("secret", appSecret);
        // 将传入的临时登录凭证 code 添加到请求参数中，js_code 为微信前端传递的临时授权码
        params.put("js_code", code);
        // 设置请求的 grant_type 参数，这里是固定值 "authorization_code"，表示使用授权码获取会话信息
        params.put("grant_type", GRANT_TYPE);

        // 发送 HTTP 请求并解析返回值
        String response = HttpUtil.post(URL, params);
        JSONObject json = JSONUtil.parseObj(response);

        // 获取 openId 并进行检查
        String openId = json.getStr("openid");
        if (openId == null || openId.isEmpty()) {
            throw new GlobalException("临时登录凭证错误，无法获取 openId");
        }
        return openId;
    }
}


