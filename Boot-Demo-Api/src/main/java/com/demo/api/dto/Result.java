package com.demo.api.dto;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}