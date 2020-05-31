package com.demo.dao.po;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 省市区三级联动表由于数据几乎无需更新,因此单表查询结果开启mybatis 二级缓存
 * 二级缓存会将查询结果映射的java对象保存在 一个map中,
 * 因此需要实现 Serializable 接口
 */
@Data
@Alias("SysAreaPO")
public class SysAreaPO implements Serializable {
    private Integer id;
    private String province;
    private String city;
    private String district;
    private String districtCode;
}
