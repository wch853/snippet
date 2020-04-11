package com.wch.snippet.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * OAuth2授权服务器配置
 *
 * @author wch
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 用户认证配置
     */
    @Resource
    private AuthenticationConfiguration authenticationConfiguration;

    /**
     * 用户认证服务
     */
    @Resource
    private UserDetailsService userDetailsService;

    /**
     * JWT 转换器
     */
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 第三方应用认证配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder builder = clients
                .inMemory().withClient("wch")
                .secret(passwordEncoder().encode("wch"))
                // .resourceIds()
                // .authorities()
                // .scopes();
                .authorizedGrantTypes("authorization_code", "password",
                        "client_credentials", "implicit", "refresh_token")
                .autoApprove("true")
                // .accessTokenValiditySeconds()
                // .refreshTokenValiditySeconds()
                .redirectUris("http://localhost:8080");
    }

    /**
     * 授权端点配置
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 自定义 JWT 生成的附加信息
        AdditionalTokenEnhancer additionalTokenEnhancer = new AdditionalTokenEnhancer();
        // JWT 生成链
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter, additionalTokenEnhancer));
        // 配置密码模式使用的用户认证配置
        endpoints.authenticationManager(authenticationConfiguration.getAuthenticationManager())
                // 配置刷新令牌的用户认证服务
                .userDetailsService(userDetailsService)
                // JWT 的加密、解密转换器、JWT生成的附加信息
                .accessTokenConverter(jwtAccessTokenConverter)
                // JWT 增加附加信息
                .tokenEnhancer(tokenEnhancerChain);
    }

    /**
     * 授权服务器安全配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // check_token 接口访问权限
        security.checkTokenAccess("authenticated");
        // token_key 接口访问权限
        security.tokenKeyAccess("authenticated");
        security.realm("security");
    }

}
