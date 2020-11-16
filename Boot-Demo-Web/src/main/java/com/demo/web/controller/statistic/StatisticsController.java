package com.demo.web.controller.statistic;

import com.demo.api.dto.Result;
import com.demo.web.listener.MyHttpSessionListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stat")
public class StatisticsController {

    @GetMapping("/getSessionNum")
    public Result getSessionCount(){
        String result = "当前会话数：" + MyHttpSessionListener.sessionCnt.intValue();
        return Result.success(result);
    }
}
