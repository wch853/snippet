package com.wch.snippet.security.config.filter;

import com.wch.snippet.security.config.properties.FormSecurityProperties;
import com.wch.snippet.security.exception.CaptchaException;
import com.wch.snippet.security.utils.Constants;
import com.wch.snippet.security.verification.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证码过滤器
 *
 * @author wch
 */
@Slf4j
public class CaptchaFilter extends OncePerRequestFilter {

    /**
     * 认证失败处理器
     */
    private AuthenticationFailureHandler failureHandler;

    /**
     * 表单登录请求匹配器
     */
    private RequestMatcher requireCaptchaValidateMatcher;

    public CaptchaFilter(FormSecurityProperties securityProperties, AuthenticationFailureHandler failureHandler) {
        this.requireCaptchaValidateMatcher =
                new AntPathRequestMatcher(securityProperties.getLogin().getProcess(), HttpMethod.POST.name());
        this.failureHandler = failureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (requireCaptchaValidate(httpServletRequest)) {
            // 匹配表单登录请求
            try {
                validateCaptcha(httpServletRequest);
            } catch (CaptchaException e) {
                log.error("user {} verification validate Exception: {}",
                        ServletRequestUtils.getStringParameter(httpServletRequest, Constants.USERNAME_FORM_NAME), e.getMessage());
                failureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                // 验证码校验失败，请求结束
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean requireCaptchaValidate(HttpServletRequest request) {
        return requireCaptchaValidateMatcher.matches(request);
    }

    /**
     * 验证图片验证码
     *
     * @param httpServletRequest
     * @throws ServletRequestBindingException
     */
    private void validateCaptcha(HttpServletRequest httpServletRequest) throws ServletRequestBindingException {
        HttpSession session = httpServletRequest.getSession();
        Captcha captchaFromSession = (Captcha) session.getAttribute(Constants.CAPTCHA_CODE);
        String captchaFromReq = ServletRequestUtils.getStringParameter(httpServletRequest, Constants.CAPTCHA_FORM_NAME);

        if (null == captchaFromSession || !StringUtils.hasText(captchaFromReq)) {
            // 表单未传验证码
            throw new CaptchaException("can not find verification");
        }

        if (captchaFromSession.isExpired()) {
            // 验证码已过期
            throw new CaptchaException("verification has expired");
        }

        if (!captchaFromSession.getCode().equals(captchaFromReq)) {
            // 验证码不匹配
            throw new CaptchaException("verification not match");
        }

        // 从session中移除验证码信息
        session.removeAttribute(Constants.CAPTCHA_CODE);
    }

}
