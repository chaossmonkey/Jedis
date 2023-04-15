package com.example.redisdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class JedisConfig {

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // Configure pool settings, e.g., max total, max idle, etc.
        return new JedisPool(poolConfig, "localhost", 6379);
    }


    @Bean
    public Jedis getMeJedis()
    {
        Jedis jedis = new Jedis("localhost");
        return jedis;
    }
}
