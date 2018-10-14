package com.wch.shiro.controller;

import com.wch.snippet.util.Response;
import com.wch.snippet.util.ResponseDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@Slf4j
@ControllerAdvice
public class ExceptionController {

    /**
     * 统一异常处理
     *
     * @return json message
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    Response exceptionHandler(Exception e) {
        log.error(e.getMessage());
        return Response.buildResponse(ResponseDefinition.ERROR);
    }

}