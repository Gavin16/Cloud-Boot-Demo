package com.demo.dao.repo;


import com.demo.dao.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Class: LoginRepository
 * @Description: TODO
 * @Author: Minsky
 * @Date: 2019/9/15 15:25
 * @Version: v1.0
 */
@Mapper
public interface UserRepository {

    UserPO selectUserById(Long id);
}
