package com.demo.api.service;

import com.demo.api.dto.request.UserDto;

public interface UserService {

    /**
     * REST接口测试
     * @date 2020-05-31 01:33:43
     * @author Minsky
     * @param id
     * @return com.demo.api.dto.request.UserDto
     */
    UserDto selectUserById(Long id);
}
