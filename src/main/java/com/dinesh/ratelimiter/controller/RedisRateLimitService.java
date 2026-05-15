package com.dinesh.ratelimiter.controller;

import io.netty.util.Timeout;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisRateLimitService {
    private final StringRedisTemplate redisTemplate;

    public RedisRateLimitService(StringRedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public Long incrementRequestCount(String key,long windowSizeMs){
        Long count= redisTemplate.opsForValue().increment(key);

        if(count!=null && count==1){
            redisTemplate.expire(key,windowSizeMs, TimeUnit.MILLISECONDS);
        }
        return count;
    }

    public Long getRemainingTtlMs(String key){
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }

}
