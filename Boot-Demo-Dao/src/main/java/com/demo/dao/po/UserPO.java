package com.demo.dao.po;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class UserPO {
    /** user 主键 id */
    private Long id;

    private String username;

    private String name;

    private BigDecimal balance;

    private Integer age;
}
