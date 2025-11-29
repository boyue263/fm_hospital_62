package com.hospital.wx.controller;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.map.MapUtil;
import com.hospital.wx.common.CommonResult;
import com.hospital.wx.controller.form.LoginOrRegisterForm;
import com.hospital.wx.exception.WxErrorCode;
import com.hospital.wx.service.PatientUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 患者用户登录Controller
 * */
@RestController
@RequestMapping("/patient/user")
@Tag(name = "PatientUserController", description = "患者登录注册管理接口")
@Slf4j
public class PatientUserController {

    @Autowired
    private PatientUserService patientUserService;
    @PostMapping("/loginOrRegister")
    @Operation(summary = "患者登录或注册")
    public CommonResult loginOrRegister(@RequestBody @Valid LoginOrRegisterForm form) {
//        System.out.println(">>>> 请求到达 /wx/patient/user/loginOrRegister <<<<"); // 添加这行
        try {
            // 调用服务层方法
            Map<String, Object> map = patientUserService.loginOrRegister(
                    form.getCode(), form.getNickname(), form.getPhoto(), form.getSex());
            String msg = MapUtil.getStr(map, "msg");
            Integer id = MapUtil.getInt(map, "id");
            // 判断是否成功
            if (msg == null || id == null) {
                // 返回失败响应，使用枚举管理错误信息
                return CommonResult.failed(WxErrorCode.LOGIN_OR_REGISTER_FAILED);
            }
            // 生成 token 信息
            StpUtil.login(id);
            String token = StpUtil.getTokenValue();
            // 返回成功响应和 token 信息
            return CommonResult.ok(msg).put(CommonResult.RETURN_TOKEN, token);
        } catch (Exception e) {
            // 记录异常日志
            log.error("登录或注册过程中发生异常", e);
            // 返回通用错误响应，给前端提示
            return CommonResult.failed(WxErrorCode.LOGIN_OR_REGISTER_FAILED);
        }
    }


}