package com.demo.api.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class QuestionDTO implements Serializable {
    private Integer id;

    private String theme;

    private String content;

    private String degree;

    private String source;

    private Date createTm;

    private Integer proficiency;

    private Integer remindTimes;

}
