package com.hospital.hms.sercive;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public interface UserService {
    Integer userLogin(Map<String,String> map);
    Set<String> selectUserPrivileges(Integer userId);
    Integer updateUserPassword(Map<String, Object> map);



}
