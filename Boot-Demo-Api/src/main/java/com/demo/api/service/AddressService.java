package com.demo.api.service;


import com.demo.api.dto.request.AddressDto;
import com.demo.api.dto.Result;
import com.demo.api.exception.ServiceException;

public interface AddressService {

    /**
     * 解析格式化的地址：地址可以不包含省市 但是地址位置要求唯一
     * @param formatedAddress
     * @return
     */
    Result parseAddress(String formatedAddress);


    /**
     *
     * @param addressDto
     * @return
     */
    Result parseAddress(AddressDto addressDto) throws ServiceException;

}
