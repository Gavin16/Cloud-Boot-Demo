package com.demo.manager.dao.user;

import com.demo.api.dto.request.UserDto;
import com.demo.dao.po.UserPO;

public interface UserManager {

    UserDto selectUserById(Long id);


    Integer updateUser(UserPO userPO);

    Integer deleteById(Long id);

    Long createUser(UserDto dto);

    Long increaceBalance(Long id);
}
