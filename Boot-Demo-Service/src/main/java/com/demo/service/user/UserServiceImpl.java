package com.demo.service.user;

import com.demo.api.dto.request.UserDto;
import com.demo.api.service.UserService;
import com.demo.common.converters.UserConverter;
import com.demo.dao.po.UserPO;
import com.demo.dao.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserRepository userRepository;

    @Override
    public UserDto selectUserById(Long id) {
        UserPO userPO = userRepository.selectUserById(id);
        UserDto userDto = UserConverter.convertToDto(userPO);
        return userDto;
    }
}
