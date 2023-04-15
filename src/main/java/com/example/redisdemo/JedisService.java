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
    public String SetUplist()
    {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.lpush("myList", "value1", "123", "value3");
            return "lol";
        }
    }
    public List<String> getMeThingsInTheList()
    {
        try(Jedis jedis=jedisPool.getResource()) {
            List<String> list=jedis.lrange("myList",0,-1);//gives all values of the list associated with the key called "myList"
            return list;
        }
    }


}
