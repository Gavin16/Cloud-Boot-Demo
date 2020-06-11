package com.demo.api.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.demo.domain
 * @Description:
 * @author: Minsky
 * @date: 2018/5/19 15:13
 */
@Data
public class IdcardDTO implements Serializable {
    private String areaFullName;

    private String gender;

    private Date birthday;

}
