package com.demo.common.converters;


import com.demo.api.dto.request.UserDto;
import com.demo.dao.po.UserPO;
import org.springframework.beans.BeanUtils;

public class UserConverter {

    public static UserDto convertToDto(UserPO userPO){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userPO,userDto);
        return userDto;
    }

    public static UserPO convertToPo(UserDto userDto){
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userDto,userPO);
        return userPO;
    }
}
