package com.demo.dao.repo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.dao.po.SysAreaPO;

public interface AreaRepository extends BaseMapper<SysAreaPO> {

    SysAreaPO getLocationByCodeLv3(long areaCode);

    SysAreaPO getLocationByCodeLv2(long areaCode);

    Long getAreaCodeById(long id);
}
