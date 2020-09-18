package com.demo.api.service;

import com.demo.api.dto.response.SysAreaDTO;

public interface AreaService {

    /**
     * ID 查询area信息
     */
    SysAreaDTO getAreaById(Long id);
}
