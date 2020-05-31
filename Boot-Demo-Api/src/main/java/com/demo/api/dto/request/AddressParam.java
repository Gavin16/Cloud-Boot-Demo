package com.demo.api.dto.request;

import java.io.Serializable;

/**
 * @Auther: Minsky
 * @Date: 2019/1/5 15:06
 * @Description:
 */
public class AddressParam implements Serializable {

    private String address;

    private String city;

    private Boolean batch;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getBatch() {
        return batch;
    }

    public void setBatch(Boolean batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "AddressParam{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", batch=" + batch +
                '}';
    }
}
