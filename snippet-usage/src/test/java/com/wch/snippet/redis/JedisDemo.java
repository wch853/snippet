package com.wch.snippet.redis;

import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis连接
 */
public class JedisDemo {

    private final String password = "";

    /**
     * 单实例
     */
    @Test
    public void demo1() {
        // 设置IP地址和端口
        Jedis jedis = new Jedis("106.14.200.121", 6379, 1000);
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
        JedisPool jedisPool = new JedisPool(config, "106.14.200.121", 6379, 1000);

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

}
