package com.wch.snippet.security.controller;

import com.wch.snippet.security.enums.ResponseEnum;
import com.wch.snippet.security.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wch
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVo exception() {
        return ResponseVo.setResponse(ResponseEnum.FAIL);
    }
}
