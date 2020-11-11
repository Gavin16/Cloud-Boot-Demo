package com.demo.web.user;

import com.demo.api.annotation.ParamValidator;
import com.demo.api.dto.Result;
import com.demo.api.dto.request.UserDto;
import com.demo.api.exception.ServiceException;
import com.demo.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/selectById/{id}")
    public Result selectUserById(@PathVariable Long id){
        UserDto userDto = userService.selectUserById(id);
        return Result.success(userDto);
    }

    @PostMapping("/update")
    @ParamValidator(fields = {"id"})
    public Result updateUser(@RequestBody UserDto dto){
        Integer integer = null;
        try {
            integer = userService.updateUser(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(integer);
    }

    @GetMapping("/user-instance")
    public Result showInfo(){
        List<ServiceInstance> instances = discoveryClient.getInstances("Cloud-Boot-Demo");
        return Result.success(instances);
    }

    @GetMapping("/log-user-instance")
    public void printUserInstance(){
        ServiceInstance instance = loadBalancerClient.choose("Cloud-Boot-Demo");
        LOGGER.info("当前访问服务:{}:{}:{}",instance.getServiceId(),instance.getHost(),instance.getPort());
    }

    @GetMapping("/delete/{id}")
    public Integer deleteById(@PathVariable Long id){
        Integer integer = null;
        try {
            integer = userService.deleteUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return integer;
    }

    @PostMapping("create")
//    @ParamValidator(fields = {"username","name","age","balance"})
    public UserDto createUser(@RequestBody UserDto dto){
        Optional<UserDto> input = Optional.ofNullable(dto);
        input.ifPresent(e->e.getUsername());

        return userService.createUser(dto);
    }
}
