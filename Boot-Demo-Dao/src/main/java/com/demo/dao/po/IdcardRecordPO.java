package com.demo.dao.po;

import lombok.Data;

import java.util.Date;


@Data
public class IdcardRecordPO {
    private String cardNo;

    private Date createTm;

    /** 创建花费时间(ms) */
    private Long costTm;
}
