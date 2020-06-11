package com.demo.api.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.demo.domain
 * @Description:
 * @author: Minsky
 * @date: 2018/5/19 15:18
 */
@Data
public class AreaDTO implements Serializable {
    private String areaName;
    private String birthday;
    private String gender;

}