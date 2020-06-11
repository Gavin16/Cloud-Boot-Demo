package com.demo.service.remote.fallback;

import com.demo.api.dto.response.SysAreaDTO;
import com.demo.service.remote.AreaFeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author: Minsky
 * @Date: 2020/6/6 22:11
 * @Description:
 */
@Component
public class AreaFeignClientFallBack implements AreaFeignClient {


    @Override
    public SysAreaDTO selectAreaById(Long id) {
        SysAreaDTO sysAreaDTO = new SysAreaDTO();
        sysAreaDTO.setCity("北京市");
        sysAreaDTO.setProvince("北京");
        sysAreaDTO.setDistrict("朝阳区");
        sysAreaDTO.setId(900002);
        return sysAreaDTO;
    }
}
