package com.demo.service.remote;

import com.demo.api.dto.response.SysAreaDTO;
import com.demo.service.remote.fallback.AreaFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Minsky
 * @Date: 2020/6/6 22:09
 * @Description:
 */
@FeignClient(name = "Could-Demo-Provider",fallback = AreaFeignClientFallBack.class)
public interface AreaFeignClient {

    @GetMapping("/area/{id}")
    SysAreaDTO selectAreaById(@PathVariable("id") Long id);
}
