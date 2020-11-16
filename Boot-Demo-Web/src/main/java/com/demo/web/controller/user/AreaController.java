package com.demo.web.controller.user;

import com.alibaba.fastjson.JSON;
import com.demo.api.dto.response.SysAreaDTO;
import com.demo.api.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author: Minsky
 * @Date: 2020/6/6 22:30
 * @Description:
 */
@RestController
@RequestMapping("/area")
@Slf4j
public class AreaController {

    @Resource
    private AreaService areaService;

    @GetMapping("/getById/{id}")
    public SysAreaDTO getAreaById(@PathVariable Long id, HttpSession session){
        session.setAttribute("aa","aa");
        log.info("查询地区id:{}", id);
        SysAreaDTO sysAreaDTO = areaService.getAreaById(id);
        return sysAreaDTO;
    }
}
