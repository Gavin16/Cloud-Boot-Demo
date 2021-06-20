package com.demo.api.dto.request;


import javax.validation.constraints.NotNull;

/**
 * @Class: LoginDto
 * @Description: TODO
 * @Author: Minsky
 * @Date: 2019/9/15 15:14
 * @Version: v1.0
 */
public class LoginDto  {
    /** user 主键 id */
    private int id;

    @NotNull(message = "用户名不能为空")
    private String loginName;

    private String userName;

    private String userPassword;

    private int userType;

    private String mobile;

    private String address;
}
