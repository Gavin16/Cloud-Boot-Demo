package com.demo.web.handler;

import com.demo.api.dto.Result;
import com.demo.api.exception.ServiceException;
import com.demo.common.enums.ResultEnum;
import com.demo.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@ControllerAdvice
public class RequestExceptionHandler {


    /**
     * 控制层返回的异常统一放在此处理
     * 匹配不同返回类型 返回不同的错误编码与描述
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        // 异常信息保存在 StringWriter 中
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));

        if (e instanceof ServiceException) {
            ServiceException addressException = (ServiceException) e;
            log.error(writer.getBuffer().toString());
            return ResultUtil.error(addressException.getCode(), addressException.getMsg());
        }

        log.error(writer.getBuffer().toString());
        return ResultUtil.error(ResultEnum.QUERY_FAILURE);

    }
}
