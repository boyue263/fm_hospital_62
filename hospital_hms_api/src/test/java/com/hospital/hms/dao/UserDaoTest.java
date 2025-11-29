package com.hospital.hms.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoTest {
    @Autowired
    UserDao userDao;
    @Test
    void login() {
        HashMap<String,String> map = new HashMap<>();
        map.put("username", "test1");
        map.put("password", "539788a47a25a62409cec4bb7f045e27");

        System.out.println(userDao.login(map));

    }

    @Test
    void selectUserPrivileges() {
        userDao.selectUserPrivileges(3).forEach(System.out::println);

    }

    @Test
    void updateUserPassword() {

            Map<String, Object> map = new HashMap<>();
            map.put("userId", "31");           // 用户ID，字符串形式
            map.put("oldPassword", "456");    // 旧密码
            map.put("newPassword", "test123");    // 新密码

            int rows = userDao.updateUserPassword(map);
            if (rows > 0) {
                System.out.println("密码更新成功！");
            } else {
                System.out.println("密码更新失败，旧密码可能错误或用户不存在！");
            }


    }
}