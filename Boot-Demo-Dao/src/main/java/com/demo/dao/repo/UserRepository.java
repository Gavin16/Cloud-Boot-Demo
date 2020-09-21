package com.demo.dao.repo;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.dao.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Class: LoginRepository
 * @Description: USER测试数据库访问
 * @Author: Minsky
 * @Date: 2019/9/15 15:25
 * @Version: v1.0
 */
@Mapper
public interface UserRepository extends BaseMapper<UserPO> {

    UserPO selectUserById(Long id);


    Integer updateUser(UserPO userPO);
}
