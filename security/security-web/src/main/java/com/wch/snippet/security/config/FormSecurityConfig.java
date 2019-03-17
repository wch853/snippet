package com.wch.snippet.security.config;

import com.wch.snippet.security.config.filter.CaptchaFilter;
import com.wch.snippet.security.config.properties.FormSecurityProperties;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 配置表单登录选项
 *
 * @author wch
 */
// @Configuration
// @EnableConfigurationProperties(FormSecurityProperties.class)
public class FormSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义用户查询服务
     */
    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 可配置安全相关属性
     */
    @Resource
    private FormSecurityProperties securityProperties;

    /**
     * 认证成功后处理器
     */
    @Resource
    private AuthenticationSuccessHandler successHandler;

    /**
     * 认证失败后处理器
     */
    @Resource
    private AuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 自定义认证服务
        auth.userDetailsService(userDetailsService)
                // 配置 bCrypt 作为密码加密方式
                .passwordEncoder(new BCryptPasswordEncoder());
        // 使用默认系统配置：注入 UserDetailsService 实现、PasswordEncoder 实现，效果一致
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 在认证登录前验证验证码
        http.addFilterBefore(new CaptchaFilter(securityProperties, failureHandler),
                UsernamePasswordAuthenticationFilter.class)
                // 表单登录，配置表单页面和表单提交URL
                .formLogin().loginPage(securityProperties.getLogin().getPage())
                .loginProcessingUrl(securityProperties.getLogin().getProcess())
                /*
                // 认证成功后重定向URL
                .successForwardUrl("/")
                // 认证失败后重定向URL
                .failureForwardUrl("/login.html?error")
                // 如果是从其它页面重定向到登录页面，则成功后跳转到原请求URL，否则跳转到指定URL
                .defaultSuccessUrl("/", false)
                // 认证失败后重定向URL
                .failureUrl("/login.html?error")
                */
                // 自定义认证成功后处理器
                .successHandler(successHandler)
                // 自定义认证失败后处理器
                .failureHandler(failureHandler)
                .and()
                .rememberMe().and()
                // 关闭 csrf 保护
                .csrf().disable()
                // 任何请求都需要认证
                .authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        // 不走 Spring Security 过滤器链
        web.ignoring()
                .mvcMatchers(securityProperties.getLogin().getPage())
                .mvcMatchers("/captcha")
                .mvcMatchers("/favicon.ico")
                .mvcMatchers("/announce");
    }

}
