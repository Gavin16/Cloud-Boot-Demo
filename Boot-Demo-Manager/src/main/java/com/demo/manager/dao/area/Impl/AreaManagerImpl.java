package com.demo.manager.dao.area.Impl;

import com.demo.dao.po.SysAreaPO;
import com.demo.dao.repo.AreaRepository;
import com.demo.manager.dao.area.AreaManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AreaManagerImpl implements AreaManager {

    @Resource
    private AreaRepository areaRepository;

    @Override
    public SysAreaPO getLocationByCodeLv3(long areaCode) {
        return areaRepository.getLocationByCodeLv3(areaCode);
    }

    @Override
    public SysAreaPO getLocationByCodeLv2(long areaCode) {
        return areaRepository.getLocationByCodeLv2(areaCode);
    }

    @Override
    public Long getAreaCodeById(long id) {
        return areaRepository.getAreaCodeById(id);
    }
}
