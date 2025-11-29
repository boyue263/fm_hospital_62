//package com.hospital.wx.config;
//import cn.dev33.satoken.stp.StpInterface;
//import com.hospital.wx.dao.UserDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Component
//public class StpInterfaceImpl implements StpInterface {
//    @Autowired
//    private UserDao userDao;
//
//    /**
//     * 返回一个用户所拥有的权限集合
//     */
//    @Override
//    public List<String> getPermissionList(Object loginId, String loginKey) {
//        int userId = Integer.parseInt(loginId.toString());
//        Set<String> permissions = userDao.selectUserPrivileges(userId);
//        ArrayList list = new ArrayList();
//        list.addAll(permissions);
//        return list;
//    }
//
//    /**
//     * 暂时不用
//     * */
//    @Override
//    public List<String> getRoleList(Object loginId, String loginKey) {
//        return null;
//    }
//}