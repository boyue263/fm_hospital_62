package com.hospital.hms.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.hospital.hms.common.CommonResult;
import com.hospital.hms.controller.form.LoginForm;
import com.hospital.hms.controller.form.UpdateUserPasswordForm;
import com.hospital.hms.sercive.UserService;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Tag(name ="userController" ,description = "用户管理接口")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public CommonResult userLogin(@Valid @RequestBody LoginForm loginForm) {
//        HashMap<String, String> hashMap = JSONUtil.parse(loginForm).toBean(HashMap.class);
//        Integer userId = userService.userLogin(hashMap);
//        CommonResult result = CommonResult.ok();
//        if (userId != null) {
//            StpUtil.login(userId);
//
//            String token = StpUtil.getTokenValue();
//            String tokenName = StpUtil.getTokenName();
//
//            Set<String> permissions = userService.selectUserPrivileges(userId);
//
//            result.put("result", "登录成功");
//            result.put("permissions", permissions);
//            result.put("token", token);
//            result.put("tokenName", tokenName);
//            log.info("User logged in: userId={}, token={}, tokenName={}", userId, token, tokenName);
//
//        }else{
//            result.put(CommonResult.RETURN_RESULT,"登录失败");
//        }
//        return result;


//        HashMap<String,Object> map = JSONUtil.parse(loginForm).toBean(HashMap.class);
        HashMap<String, String> map = BeanUtil.toBean(loginForm, HashMap.class);
        Integer userId = userService.userLogin(map);
        CommonResult result = CommonResult.ok();
        if (userId != null) {
            StpUtil.login(userId);
            String token = StpUtil.getTokenValue();
            String tokenName = StpUtil.getTokenName();
            Set<String> permissions = userService.selectUserPrivileges(userId);

            result.put("result", "登录成功");
            result.put("permissions", permissions);

            result.put("token", token);
            result.put("tokenName", tokenName);
            log.info("User logged in: userId={}, token={}, tokenName={}", userId, token, tokenName);

        }else{
            result.put(CommonResult.RETURN_RESULT,"登录失败");
        }
        return result;
    }



    @GetMapping("/logout")
    @SaCheckLogin
    @Operation(summary = "退出登录")
    public CommonResult logout() {
        StpUtil.logout();
        return CommonResult.ok();
    }
    @PostMapping("/updatePassword")
    @SaCheckLogin
    @Operation(summary = "修改密码")
    public CommonResult updatePassword(@Valid @RequestBody UpdateUserPasswordForm updateUserPasswordForm) {
        Integer userId =StpUtil.getLoginIdAsInt();
        Map<String, Object> param = BeanUtil.beanToMap(updateUserPasswordForm);
        param.put("userId", userId);
        int rows = userService.updateUserPassword(param);
        return CommonResult.ok().put(CommonResult.RETURN_RESULT,rows);
    }

}
