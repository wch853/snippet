package com.wch.test.redis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisDemo {

    @Value("${spring.redis.password}")
    private String password;

    /**
     * 单实例
     */
    @Test
    public void demo1() {
        // 设置IP地址和端口
        Jedis jedis = new Jedis("106.14.200.121", 6379);
        // 设置密码
        jedis.auth(password);

        jedis.set("name", "wch");
        String name = jedis.get("name");
        System.out.println(name);

        // 释放资源
        jedis.close();
    }

    /**
     * 连接池
     */
    @Test
    public void demo2() {
        // 获取连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(30);
        // 设置最大空闲连接数
        config.setMaxIdle(10);

        // 获得连接池
        JedisPool jedisPool = new JedisPool(config, "106.14.200.121", 6379);

        // 获得Jedis对象
        Jedis jedis = null;
        try {
            // 通过连接池获得连接
            jedis = jedisPool.getResource();
            jedis.auth(password);
            jedis.set("name", "wch");
            String name = jedis.get("name");
            System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (null != jedis) {
                jedis.close();
            }
            if (null != jedisPool) {
                jedisPool.close();
            }
        }
    }

    @Test
    public void demo3() {
        System.out.println(password);
    }
}
