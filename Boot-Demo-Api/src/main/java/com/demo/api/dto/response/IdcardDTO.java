package com.demo.api.dto.response;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.demo.domain
 * @Description:
 * @author: Minsky
 * @date: 2018/5/19 15:13
 */
public class IdcardDTO implements Serializable {
    private String areaFullName;

    private String gender;

    private Date birthday;

    public String getAreaFullName() {
        return areaFullName;
    }

    public void setAreaFullName(String areaFullName) {
        this.areaFullName = areaFullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "IdcardDTO{" +
                "areaFullName='" + areaFullName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
