package com.wch.snippet.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wch.snippet.security.enums.ResponseEnum;
import com.wch.snippet.security.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败json处理器
 * @author wch
 */
// @Component
public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(ResponseVo.dynamicResponse(ResponseEnum.UNAUTHORIZED, exception.getMessage())));
    }
}
