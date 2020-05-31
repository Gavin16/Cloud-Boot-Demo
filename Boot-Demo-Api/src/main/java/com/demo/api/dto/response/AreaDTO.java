package com.demo.api.dto.response;

import java.io.Serializable;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.demo.domain
 * @Description:
 * @author: Minsky
 * @date: 2018/5/19 15:18
 */
public class AreaDTO implements Serializable {
    private String areaName;
    private String birthday;
    private String gender;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "AreaDTO{" +
                "areaName='" + areaName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}