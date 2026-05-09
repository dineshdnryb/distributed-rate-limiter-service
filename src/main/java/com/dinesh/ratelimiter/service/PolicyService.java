package com.dinesh.ratelimiter.service;

import com.dinesh.ratelimiter.model.RateLimitPolicy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PolicyService {
    private final Map<String, RateLimitPolicy> policy=new HashMap<>();

    public PolicyService(){
        policy.put("payment", new RateLimitPolicy("payment", 10, 60_000, "FIXED_WINDOW"));
        policy.put("login", new RateLimitPolicy("login", 5, 60_000, "FIXED_WINDOW"));
        policy.put("search", new RateLimitPolicy("search", 100, 60_000, "FIXED_WINDOW"));
    }

    public RateLimitPolicy getPolicy(String apiName){
        return policy.getOrDefault(apiName,new RateLimitPolicy(apiName,5,10_000,"FIXED_WINDOW"));
    }

}
