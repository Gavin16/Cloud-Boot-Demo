package com.demo.service.business.user;

import com.demo.api.dto.request.UserDto;
import com.demo.api.service.UserService;
import com.demo.common.converters.UserConverter;
import com.demo.dao.po.UserPO;
import com.demo.dao.repo.UserRepository;
import com.demo.manager.dao.user.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserManager userManager;

    @Override
    public UserDto selectUserById(Long id) {
        return userManager.selectUserById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateUser(UserDto userDto) {
        UserPO userPO = UserConverter.convertToPo(userDto);
        Integer integer = userManager.updateUser(userPO);
        return integer;
    }

    @Override
    public Integer deleteUserById(Long id) {
        return userManager.deleteById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDto createUser(UserDto dto) {
        Long userId = userManager.createUser(dto);
        return userManager.selectUserById(userId);
    }

}
