package com.dinesh.ratelimiter.service;

import com.dinesh.ratelimiter.controller.RedisRateLimitService;
import com.dinesh.ratelimiter.model.FixedWindowCounter;
import com.dinesh.ratelimiter.dto.RateLimitCheckRequest;
import com.dinesh.ratelimiter.dto.RateLimitCheckResponse;
import com.dinesh.ratelimiter.model.RateLimitPolicy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RateLimitService {
    public final PolicyService policyService;
    private final RedisRateLimitService rateLimitService;

    public RateLimitService(PolicyService policyService, RedisRateLimitService rateLimitService){
        this.policyService=policyService;
        this.rateLimitService = rateLimitService;
    }

    public RateLimitCheckResponse checkRateLimit(RateLimitCheckRequest rateLimitCheckRequest){
        RateLimitPolicy rateLimitPolicy=policyService.getPolicy(rateLimitCheckRequest.getApiName());
        String key= "rl:"+rateLimitCheckRequest.getClientId()+":"+rateLimitCheckRequest.getApiName();

        int limit=rateLimitPolicy.getLimit();
        long windowSizeMs=rateLimitPolicy.getWindowSizeMs();

        Long count=rateLimitService.incrementRequestCount(key,windowSizeMs);


        if(count<=limit){
            return new RateLimitCheckResponse(
                    rateLimitCheckRequest.getAlgorithm(),
                    0,
                    limit-count.intValue(),
                    true);
        }

        Long TTLValidation = rateLimitService.getRemainingTtlMs(key);

        return new RateLimitCheckResponse(
                rateLimitCheckRequest.getAlgorithm(),
                TTLValidation,
                0,
                false
        );




    };

}
