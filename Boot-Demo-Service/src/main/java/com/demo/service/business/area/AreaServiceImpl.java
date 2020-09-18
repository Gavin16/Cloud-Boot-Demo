package com.demo.service.business.area;

import com.demo.api.dto.response.SysAreaDTO;
import com.demo.api.service.AreaService;
import com.demo.manager.remote.AreaFeignClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class AreaServiceImpl implements AreaService {


    @Resource
    private AreaFeignClient areaFeignClient;

    @Override
    public SysAreaDTO getAreaById(Long id) {
        return areaFeignClient.selectAreaById(id);
    }
}
