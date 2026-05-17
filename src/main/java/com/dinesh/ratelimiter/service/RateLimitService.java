package com.dinesh.ratelimiter.service;

import com.dinesh.ratelimiter.dto.RateLimitCheckRequest;
import com.dinesh.ratelimiter.dto.RateLimitCheckResponse;
import com.dinesh.ratelimiter.model.RateLimitPolicy;
import org.springframework.stereotype.Service;

import java.util.List;

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

        List resultFromScript=rateLimitService.executeRateLimitScript(
                key,rateLimitPolicy.getLimit(),windowSizeMs);

        boolean allowed = ((Long) resultFromScript.get(0)) == 1;
        int remaining = ((Long) resultFromScript.get(1)).intValue();
        long retryAfterMs = ((Long) resultFromScript.get(2));


        return new RateLimitCheckResponse(
                rateLimitCheckRequest.getAlgorithm(),
                retryAfterMs,
                remaining,
                allowed
        );


    };

}
