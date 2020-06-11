package com.demo.api.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.demo.domain
 * @Description:
 * @author: Minsky
 * @date: 2018/5/19 20:11
 */
@Data
public class SysAreaDTO implements Serializable {
    private Integer id;
    private String province;
    private String city;
    private String district;
    private String districtCode;

}
