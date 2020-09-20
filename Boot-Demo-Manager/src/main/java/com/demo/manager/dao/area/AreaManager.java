package com.demo.manager.dao.area;

import com.demo.dao.po.SysAreaPO;

public interface AreaManager {
    SysAreaPO getLocationByCodeLv3(long areaCode);

    SysAreaPO getLocationByCodeLv2(long areaCode);

    Long getAreaCodeById(long id);
}
