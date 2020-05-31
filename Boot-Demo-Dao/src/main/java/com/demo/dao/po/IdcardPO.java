package com.demo.dao.po;

import lombok.Data;

import java.util.Date;

@Data
public class IdcardPO {
    private String areaFullName;

    private String gender;

    private Date birthday;
}
