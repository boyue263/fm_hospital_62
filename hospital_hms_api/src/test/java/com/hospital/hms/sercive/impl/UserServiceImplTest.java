package com.hospital.hms.sercive.impl;

import com.hospital.hms.sercive.UserService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
@Autowired
UserServiceImpl userServiceImpl;
    @Test
    void userLogin() {
        HashMap<String,String> map = new HashMap<>();
        map.put("username","zhangsan");
        map.put("password","zhangsan");
        System.out.println(userServiceImpl.userLogin(map));
    }

    @Test
    void encryptPassword() {
        System.out.println(userServiceImpl.encryptPassword("zhangsan", "zhangsan"));
    }
}