package com.wch.snippet.security.controller;

import com.wch.snippet.security.enums.ResponseEnum;
import com.wch.snippet.security.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author wch
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * 未认证异常
     *
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseVo unauthorized() {
        return ResponseVo.setResponse(ResponseEnum.UNAUTHORIZED);
    }

    /**
     * 无权限异常
     *
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseVo forbidden() {
        return ResponseVo.setResponse(ResponseEnum.FORBIDDEN);
    }

    /**
     * 兜底异常
     *
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVo exception() {
        return ResponseVo.setResponse(ResponseEnum.FAIL);
    }
}
