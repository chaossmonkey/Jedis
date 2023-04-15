package com.example.redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private  JedisService jedisService;

    @Autowired
    public MyController(JedisService jedisService) {
        this.jedisService = jedisService;
    }

//    http://localhost:8080/set?key=gen&value=123
    @GetMapping("/set")
    public String set(@RequestParam("key") String key, @RequestParam("value") String value) {
        jedisService.setValue(key,value);
        return "Value set successfully";
    }

    @GetMapping("/get")
    public String get(@RequestParam("key") String key) {
        return jedisService.getValue(key);
    }

    @GetMapping("/randomApiForList")
    public void randomApiForList()
    {
        jedisService.randomThingsOnList();
    }
}
