package com.demo.api.dto.response;

import lombok.Data;

import java.io.Serializable;
@Data
public class QuestionOptionDto implements Serializable {

    private String path;

    private String source;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
