package com.wch.snippet.redis;

import com.wch.snippet.SpringTestBase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.testng.annotations.Test;

import javax.annotation.Resource;

@SpringBootTest
public class RedisIntegratedTest extends SpringTestBase {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test1() {
        System.out.println(redisTemplate.opsForValue().get("pId1").getClass().getName());
    }
}
