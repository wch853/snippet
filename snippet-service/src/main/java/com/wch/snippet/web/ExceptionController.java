package com.wch.snippet.web;

import com.wch.snippet.util.Response;
import com.wch.snippet.util.ResponseDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常控制器
 */
@Slf4j
@ControllerAdvice
public class ExceptionController {

    /**
     * 统一异常处理
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    Response exceptionHandler(Exception e) {
        log.error(e.getMessage());
        return Response.buildResponse(ResponseDefinition.ERROR, e.getMessage());
    }

}
