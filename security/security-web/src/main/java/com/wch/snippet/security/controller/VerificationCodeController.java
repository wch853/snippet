package com.wch.snippet.security.controller;

import com.wch.snippet.security.config.properties.CustomSecurityProperties;
import com.wch.snippet.security.utils.Constants;
import com.wch.snippet.security.verification.Captcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码相关接口
 *
 * @author wch
 */
@RestController("/verification")
public class VerificationCodeController {

    @Resource
    private CustomSecurityProperties securityProperties;

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成图形验证码
        Captcha captcha =
                Captcha.create(securityProperties.getCaptcha().getWidth(),
                        securityProperties.getCaptcha().getHeight(),
                        securityProperties.getCaptcha().getLength(),
                        securityProperties.getCaptcha().getExpireIn());
        // 将验证码信息放入 session
        request.getSession().setAttribute(Constants.CAPTCHA_CODE, captcha);

        // 返回图形验证码
        ImageIO.write(captcha.getImage(), Constants.CAPTCHA_IMAGE_FORMAT_NAME,
                response.getOutputStream());
    }

}
