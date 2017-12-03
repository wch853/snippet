package com.wch.test.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wch.test.common.CommonConstants;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 缓存key生成策略，全限定名#方法名:参数.toString()...
     *
     * @return KeyGenerator
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(CommonConstants.CLASS_METHOD_SEPERATOR);
                sb.append(method.getName());
                for (Object param : params) {
                    sb.append(CommonConstants.CACHE_SEPERATOR);
                    sb.append(String.valueOf(param));
                }
                return sb.toString();
            }
        };
    }

    /**
     * 配置redis缓存管理器
     *
     * @return CacheManager
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    /**
     * 自定义RedisTemplate
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        /*
          RedisTemplate的key、value默认使用JdkSerializationRedisSerializer
          StringRedisTemplate的key、value默认使用StringRedisSerializer
          当key/value不是String类型时会报java.lang.ClassCastException，序列化错误
          当key/value为其它类型时，需要配置相应的Serializer
         */

        /*
        // 配置String-Object类型的redisTemplate
        RedisTemplate<String, Object> redisTemplate = new StringRedisTemplate(factory);
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        */

        // 配置String-jsonString类型的redisTemplate
        RedisTemplate<String, String> redisTemplate = new StringRedisTemplate(factory);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
