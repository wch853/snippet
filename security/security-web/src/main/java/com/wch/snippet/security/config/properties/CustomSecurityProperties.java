package com.wch.snippet.security.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wch
 */
@Getter
@Setter
@ConfigurationProperties("custom.security")
public class CustomSecurityProperties {

    /**
     * 登录配置
     */
    private Login login = new Login();

    /**
     * 验证码配置
     */
    private Captcha captcha = new Captcha();

    @Getter
    @Setter
    public static class Login {

        /**
         * 登录页面URL
         */
        private String page = "/login";

        /**
         * 登录表单处理URL
         */
        private String process = "/login";
    }

    @Getter
    @Setter
    public static class Captcha {

        /**
         * 验证码宽度
         */
        private int width;

        /**
         * 验证码高度
         */
        private int height;

        /**
         * 验证码位数
         */
        private int length = 4;

        /**
         * 验证码过期时间（秒）
         */
        private int expireIn = 60;
    }

}
