package com.demo.api.dto;


import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String requestId;
    private Boolean success;
    private Integer business;
    private String code;
    private String message;
    private String date;
    private String version;
    private T obj;

    private static final String SUCCESS_CODE = "10000";

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
    public Result(boolean success) {
        this.success = success;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T obj) {
        Result<T> result = new Result<>(true);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.setDate(sdf.format(new Date()));
        result.setCode(SUCCESS_CODE);
        result.setObj(obj);
        return result;
    }

    public static <T> Result<T> error(String message) {
        return error(message, null);
    }

    public static <T> Result<T> error(String message, String code) {
        Result<T> result = new Result<>(false);
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    public Result(String requestId, boolean success) {
        super();
        this.requestId = requestId;
        this.success = success;
    }

    public boolean isSuccess() {
        if (null == success) {
            return false;
        }
        return success;
    }

}