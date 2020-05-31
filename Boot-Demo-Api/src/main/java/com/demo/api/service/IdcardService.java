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
    Result parseIdcard(String idcardNO);

    /**
     * 查询区域名称
     * @param areaCode
     * @return
     */
    String getAreaInfo(String areaCode);

    /**
     * 解析生日是否是合法日期
     * @param dateNumSeries
     * @return
     */
    String getBirthday(String dateNumSeries);

    /**
     * 判断整张卡是否符合校验规则
     * @param fullIdcardSeries
     * @return
     */
    Boolean checkIdcard(String fullIdcardSeries);

    /**
     * 解析性别
     * @param idCardNumber
     * @return
     */
    String getGender(String idCardNumber);

    /**
     * 生成一个有效的身份证
     * @return
     */
    Result generateIdcard();
}
