package com.hospital.hms.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface UserDao {
      Integer login(Map<String,String> map);

      Set<String> selectUserPrivileges(Integer userId);
/**
 *
 */
      Integer updateUserPassword(Map<String, Object> map);


}
