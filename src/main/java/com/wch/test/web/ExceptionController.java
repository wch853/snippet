package com.wch.test.web;

import com.wch.test.enums.ResultEnum;
import com.wch.test.domain.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常控制器
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 统一异常处理
     *
     * @return json message
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    Result exceptionHandler(Exception e) {
        LOGGER.error(e.getMessage());
        if (e instanceof UnauthorizedException) {
            return Result.response(ResultEnum.UNAUTHORIZED, e.getMessage(), null);
        }
        return Result.response(ResultEnum.FAIL, e.getMessage(), null);
    }

}
