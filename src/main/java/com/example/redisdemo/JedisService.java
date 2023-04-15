package com.example.redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class JedisService {


    private final JedisPool jedisPool;

  @Autowired
    public JedisService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void setValue(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        }
    }

    public String getValue(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    public void randomThingsOnList()
    {
        try (Jedis jedis = jedisPool.getResource()) {

            jedis.lpush("List-KEY", "value1", "value2", "value3");

            // Get all the elements in the list by specifying the key and the range (start and end indices)
            List<String> listValues = jedis.lrange("myList", 0, -1);

            // Print the list values
            for (String value : listValues) {
                System.out.println(value);
            }
        }
    }
}
