package com.demo.service.business.idcard;

import com.demo.api.service.TestService;
import com.demo.manager.redis.BatchSaveManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private BatchSaveManager batchSaveManager;

    @Override
    public void testBatchSave() {
        batchSaveManager.bathSave();
    }
}
