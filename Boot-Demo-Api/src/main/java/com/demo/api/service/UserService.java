package com.demo.api.service;

import com.demo.api.dto.request.UserDto;
import com.demo.api.exception.ServiceException;

public interface UserService {

    /**
     * REST接口测试
     * @date 2020-05-31 01:33:43
     * @author Minsky
     * @param id
     * @return com.demo.api.dto.request.UserDto
     */
    UserDto selectUserById(Long id);

    /**
     * 修改User 信息
     * @param userDto
     * @return
     */
    Integer updateUser(UserDto userDto) throws Exception;

    /**
     * MyBatis 逻辑删除测试
     * @param id
     * @return
     */
    Integer deleteUserById(Long id) throws Exception;

    /**
     * 新增用户
     * @return
     */
    UserDto createUser(UserDto dto);
}
