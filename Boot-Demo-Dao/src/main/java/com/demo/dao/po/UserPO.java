package com.demo.dao.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;


@Data
@TableName("user")
public class UserPO {
    /** user 主键 id */
    private Long id;

    private String username;

    private String name;

    private BigDecimal balance;

    private Integer age;

    @TableLogic
    private Integer deleted;
}
