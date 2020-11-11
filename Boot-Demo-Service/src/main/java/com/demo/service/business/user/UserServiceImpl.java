package com.demo.service.business.user;

import com.demo.api.dto.request.UserDto;
import com.demo.api.exception.ServiceException;
import com.demo.api.service.UserService;
import com.demo.common.converters.UserConverter;
import com.demo.dao.po.UserPO;
import com.demo.manager.dao.user.UserManager;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.execute.Execute;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public Integer updateUser(UserDto userDto) throws Exception{
        UserPO userPO = UserConverter.convertToPo(userDto);
        Integer integer = userManager.updateUser(userPO);
        if(userDto.getId() == 1){
            throw new ServiceException("ID为1的记录不能修改");
        }
        return integer;
    }



    /**
     * 验证声明式事务的几种传播机制:
     * PROPAGATION.REQUIRED : 默认传播机制, 表示当前方法必须在一个具有事务的上下文中运行，如有客户端有事务在进行，那么被调用端将在该事务中运行，否则的话重新开启一个事务
     * PROPAGATION.NESTED ：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则按 REQUIRED 属性执行。
     * PROPAGATION.REQUIRES_NEW： 表示当前方法必须运行在它自己的事务中。一个新的事务将启动，而且如果有一个现有的事务在运行的话，则这个方法将在运行期被挂起，直到新的事务提交或者回滚才恢复执行。
     *
     * !!--注意--!!
     *  Transactional 注解声明式事务修饰方法时 如果方法在同一个类中,那么默认的被修饰方法是不能被拦截的，
     *  因此要想事务被拦截，或事务按照指定的传播机制生效，需要将事务定义在不同类的方法中
     *
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteUserById(Long id) throws Exception{
        Integer integer = userManager.deleteById(id);
        userManager.increaceBalance(id);
        if(id == 3){
            throw new Exception("id为3的记录不能被删除");
        }
        return integer;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDto createUser(UserDto dto) {
        Long userId = userManager.createUser(dto);
        return userManager.selectUserById(userId);
    }

}
