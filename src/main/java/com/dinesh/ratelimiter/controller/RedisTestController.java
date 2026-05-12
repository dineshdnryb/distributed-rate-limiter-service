package com.dinesh.ratelimiter.controller;

import com.dinesh.ratelimiter.service.RedisTestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/redis")
public class RedisTestController {

    private final RedisTestService redisTestService;

    public RedisTestController(RedisTestService redisTestService) {
        this.redisTestService = redisTestService;
    }

    @PostMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam String value){
        redisTestService.saveValue(key,value);
        return value;
    }

    @GetMapping("/get")
    public String getValue(@RequestParam String key){
        return redisTestService.getValue(key);
    }

    @PostMapping("/increment")
    public Long increment(@RequestParam String key){
        return redisTestService.incrementValue(key);
    }
}
