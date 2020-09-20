package com.demo.api.service;

import com.demo.api.dto.Result;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.demo.service
 * @Description:
 * @author: Minsky
 * @date: 2018/5/19 15:11
 */
public interface IdcardService {

    /**
     * 解析身份证号码
     * @param idcardNO
     * @return
     */
//    Result parseIdcard(String idcardNO);


    /**
     * 生成一个有效的身份证
     * @return
     */
    String generateIdcard();
}
