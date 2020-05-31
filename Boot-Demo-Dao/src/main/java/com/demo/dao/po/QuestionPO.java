package com.demo.dao.po;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionPO {

    private Integer id;

    private String theme;

    private String content;

    private String degree;

    private String source;

    private Date createTm;

    private Integer proficiency;

    private Integer remindTimes;
}
