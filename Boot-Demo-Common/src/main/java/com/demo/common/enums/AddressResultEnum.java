package com.demo.common.enums;

/**
 * @Auther: Minsky
 * @Date: 2019/1/5 15:23
 * @Description:
 */
public enum AddressResultEnum {
    EXT_SERVICE_MSG("200100","未知的地址"),
    PARAM_EMPTY_ERROR("200101","必传参书为空"),
    SYS_BUSY_ERROR("200101","服务器忙，请稍后再试"),
    ADDRESS_UNKNOW("200102","未知的地址");

    private String code;
    private String msg;

    AddressResultEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
