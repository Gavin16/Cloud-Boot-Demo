package com.demo.dao.repo;

import com.demo.dao.po.IdcardRecordPO;

/**
 * @Class: IdcardRepository
 * @Description: TODO
 * @Author: Minsky
 * @Date: 2019/9/2 7:32
 * @Version: v1.0
 */
public interface IdcardRepository {

    int insertRecord(IdcardRecordPO recordPO);
}
