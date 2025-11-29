package com.hospital.hms.controller;

import com.hospital.hms.common.CommonResult;
import com.hospital.hms.controller.form.LoginForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    void userLogin() {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("zhangsan");
        loginForm.setPassword("zhangsan");

        // 调用 userLogin 方法
        CommonResult result = userController.userLogin(loginForm);

        // 断言登录成功
        assertEquals("登录成功", result.get("result"));

        // 断言返回的权限集合存在且非空
        assertNotNull(result.get("permissions"));
        assertFalse(((java.util.Set<?>) result.get("permissions")).isEmpty());

        // 断言token和tokenName不为空
        assertNotNull(result.get("token"));
        assertNotNull(result.get("tokenName"));
    }
}