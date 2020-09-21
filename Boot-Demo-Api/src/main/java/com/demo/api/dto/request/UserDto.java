package com.demo.api.dto.request;

import com.demo.api.annotation.UseParamValidator;
import lombok.Data;

import java.math.BigDecimal;


/**
 * @Class: UserDto
 * @Description:  登录之后返回给 session及调用端使用的DTO
 * @Author: Minsky
 * @Date: 2019/9/15 15:43
 * @Version: v1.0
 */
@Data
@UseParamValidator
public class UserDto {
    /** user 主键 id */
    private Long id;

    private String username;

    private String name;

    private BigDecimal balance;

    private Integer age;
}
