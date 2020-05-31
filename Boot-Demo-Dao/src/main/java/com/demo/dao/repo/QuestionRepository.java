package com.demo.dao.repo;

import com.demo.dao.po.QuestionPO;

import java.util.List;

public interface QuestionRepository {

    QuestionPO getById(Integer id);

    int incrRemindTimes(Integer id);

    /** 批量插入面试题 */
    int batchInsert(List<QuestionPO> questionPOS);

    /** 总面试题数量 */
    int totalRecordsNum();
}
