package com.demo.api.dto.response;

import java.io.Serializable;
import java.util.Date;

public class QuestionDTO implements Serializable {
    private Integer id;

    private String theme;

    private String content;

    private String degree;

    private String source;

    private Date createTm;

    private Integer proficiency;

    private Integer remindTimes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    public Integer getProficiency() {
        return proficiency;
    }

    public void setProficiency(Integer proficiency) {
        this.proficiency = proficiency;
    }

    public Integer getRemindTimes() {
        return remindTimes;
    }

    public void setRemindTimes(Integer remindTimes) {
        this.remindTimes = remindTimes;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", content='" + content + '\'' +
                ", degree='" + degree + '\'' +
                ", source='" + source + '\'' +
                ", createTm=" + createTm +
                ", proficiency=" + proficiency +
                ", remindTimes=" + remindTimes +
                '}';
    }
}
