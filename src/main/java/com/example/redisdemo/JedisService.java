package com.example.redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

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


    //DIFFRENCE BTWN A SET AND HASHMAP

//    A Set is a collection of unique elements, where each element can only appear once.
//    Sets do not maintain any specific order for their elements. In programming languages like Java,
//    a Set is often implemented as an interface with different concrete implementations like HashSet and TreeSet.
//
//    A Map (or dictionary, associative array) is a collection of key-value pairs.
//    Each key is unique and maps to a corresponding value.
//    The keys in a Map are unordered, and the values can be duplicated, but the keys must be unique.
//    In Java, a Map is an interface with various concrete implementations like HashMap and TreeMap.
//
//    The primary difference between a Set and a Map is the way they store data:
//
//    A Set stores unique elements without any associated data.
//    A Map stores unique keys with associated values.
    public void randomOpsOnSet()
    {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.sadd("mySet", "value1", "value2", "value3");

            // Get all elements from the set
            Set<String> setValues = jedis.smembers("mySet");

            // Print the set values
            for (String value : setValues) {
                System.out.println(value);
            }
        }
    }

    public void randomOpsOnSortedSet()
    {
        try (Jedis jedis = jedisPool.getResource()) {

            jedis.zadd("mySortedSet", 1, "value1");
            jedis.zadd("mySortedSet", 3, "value2");
            jedis.zadd("mySortedSet", 2, "value3");

            // Get all elements from the sorted set with their scores
            Set<Tuple> sortedSetValues = jedis.zrangeWithScores("mySortedSet", 0, -1);

            // Print the sorted set values and their scores
            for (Tuple tuple : sortedSetValues) {
                System.out.println("Value: " + tuple.getElement() + ", Score: " + tuple.getScore());
            }
        }
        }
    }



