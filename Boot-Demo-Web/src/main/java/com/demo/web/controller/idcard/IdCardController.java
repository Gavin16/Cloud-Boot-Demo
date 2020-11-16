package com.demo.web.controller.idcard;

import com.demo.api.dto.Result;
import com.demo.api.service.IdcardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/idcard")
public class IdCardController {

    @Resource
    private IdcardService idcardService;

    @GetMapping("generate")
    public Result genRendomIdcard(){
        String cardNo = idcardService.generateIdcard();
        return Result.success(cardNo);
    }
}
