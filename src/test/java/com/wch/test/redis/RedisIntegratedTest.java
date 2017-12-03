package com.wch.test.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisIntegratedTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test1() {
        System.out.println(redisTemplate.opsForValue().get("pId1").getClass().getName());
    }
}
