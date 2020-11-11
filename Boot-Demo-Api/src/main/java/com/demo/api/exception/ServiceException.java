package com.demo.api.exception;

import java.io.Serializable;

public class ServiceException extends RuntimeException implements Serializable {

    private String code;
    private String msg;

    public ServiceException(){}

    public ServiceException(String msg){
        super(msg);
    }

    public ServiceException(String code , String msg){
        super(msg);
        this.code = code ;
        this.msg = msg;
    }

    /**
     * 服务端异常不打印栈信息
     */
    @Override
    public void printStackTrace() {
        printStackTrace(System.err);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
