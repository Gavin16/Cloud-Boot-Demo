package com.demo.manager.dao.user.impl;

import com.demo.api.dto.request.UserDto;
import com.demo.api.utils.BeanConverter;
import com.demo.dao.po.UserPO;
import com.demo.dao.repo.UserRepository;
import com.demo.manager.dao.user.UserManager;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class UserManagerImpl implements UserManager {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDto selectUserById(Long id) {
        UserPO userPO = userRepository.selectUserById(id);
        return BeanConverter.convert(userPO,UserDto.class);
    }

    @Override
    public Integer updateUser(UserPO userPO) {
        return userRepository.updateUser(userPO);
    }

    @Override
    public Integer deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Long createUser(UserDto dto) {
        UserPO userPO = BeanConverter.convert(dto, UserPO.class);
        return userRepository.createUser(userPO);
    }


    /**
     * 验证事务传播机制
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NESTED)
    public Long increaceBalance(Long id) {
        UserPO userPO = userRepository.selectUserById(id-1);
        return userRepository.increaceBalance(userPO);
    }

}
