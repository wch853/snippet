package com.wch.snippet.security.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * JWT 自定义enhancer
 *
 * @author wch
 */
public class AdditionalTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
        Map<String, Object> additionalInformation = new LinkedHashMap<>(result.getAdditionalInformation());
        additionalInformation.put("enhancer", "addition");
        result.setAdditionalInformation(additionalInformation);
        return result;
    }
}
