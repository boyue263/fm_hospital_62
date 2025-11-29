package com.hospital.hms.sercive.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.hospital.hms.dao.DeptDao;
import com.hospital.hms.dao.UserDao;
import com.hospital.hms.sercive.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final int PREFIX_LENGTH = 6;
    private static final int SUFFIX_LENGTH = 3;

    @Autowired
    private UserDao userDao;


    //    @Autowired
//    private StpInterface stpInterface;
    @Override
    public Integer userLogin(Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");

        password = encryptPassword(username, password);
        map.put("password",password);
        return userDao.login(map);
    }
    public String encryptPassword(String username, String password) {
        MD5 md5 = MD5.create();

        String temp = md5.digestHex(username);

        String tempStart = StrUtil.subWithLength(temp, 0, PREFIX_LENGTH);
        String tempEnd = StrUtil.subSuf(temp, temp.length() - SUFFIX_LENGTH);

        return md5.digestHex(tempStart+password+tempEnd);
    }

    @Override
    public Set<String> selectUserPrivileges(Integer userId) {
        return userDao.selectUserPrivileges(userId);
    }

    @Override
    public Integer updateUserPassword(Map<String, Object> map) {
        return userDao.updateUserPassword(map);
    }


}
