package com.wch.snippet.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wch.snippet.security.enums.ResponseEnum;
import com.wch.snippet.security.vo.ResponseVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功json处理器
 *
 * @author wch
 */
// @Component
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(ResponseVo.setResponse(ResponseEnum.SUCCESS, authentication)));
    }
}
