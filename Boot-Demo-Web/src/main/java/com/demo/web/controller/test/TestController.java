package com.demo.web.controller.test;

import com.demo.api.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/batchSave")
    public void testRedisBatchSave(){
        testService.testBatchSave();
    }
}
