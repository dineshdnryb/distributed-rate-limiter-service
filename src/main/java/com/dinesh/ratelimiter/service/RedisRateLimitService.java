package com.dinesh.ratelimiter.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisRateLimitService {
    private final StringRedisTemplate redisTemplate;
    private final DefaultRedisScript<List> fixedWindowRedisScript;

    public RedisRateLimitService(StringRedisTemplate redisTemplate, DefaultRedisScript<List> fixedWindowRedisScript){
        this.redisTemplate=redisTemplate;
        this.fixedWindowRedisScript = fixedWindowRedisScript;
    }

    public List executeRateLimitScript(String key,int limit,long windowSizeMs){
        return redisTemplate.execute(
                fixedWindowRedisScript,
                Collections.singletonList(key),
                String.valueOf(limit),
                String.valueOf(windowSizeMs));

    }

    // depreciated
    public Long incrementRequestCount(String key,long windowSizeMs){
        Long count= redisTemplate.opsForValue().increment(key);

        if(count!=null && count==1){
            redisTemplate.expire(key,windowSizeMs, TimeUnit.MILLISECONDS);
        }
        return count;
    }

    // Depreciated
    public Long getRemainingTtlMs(String key){
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }

}
