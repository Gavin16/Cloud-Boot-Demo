package com.demo.service.business.user;

import com.demo.api.dto.request.UserDto;
import com.demo.api.service.UserService;
import com.demo.common.converters.UserConverter;
import com.demo.dao.po.UserPO;
import com.demo.dao.repo.UserRepository;
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
    private UserRepository userRepository;

    @Override
    public UserDto selectUserById(Long id) {
        UserPO userPO = userRepository.selectUserById(id);
        UserDto userDto = UserConverter.convertToDto(userPO);
        return userDto;
    }


    @Override
    public Integer updateUser(UserDto userDto) {
        UserPO userPO = UserConverter.convertToPo(userDto);
        Integer integer = null;
        try {
            integer = updateUser(userPO);
        } catch (Exception e) {
            log.error("user update error：" ,e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return integer;
    }

    @Override
    public Integer deleteUserById(Long id) {
        return userRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer updateUser(UserPO userPO){
        Integer integer = userRepository.updateUser(userPO);
        Long id = userPO.getId();
        if(id == 1){
            throw new RuntimeException("test transaction..");
        }
        return integer;
    }
}
