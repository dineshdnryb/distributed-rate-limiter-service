package com.dinesh.ratelimiter.service;

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
    private final Map<String,FixedWindowCounter> requestCounters=new HashMap<>();

    public RateLimitService(PolicyService policyService){
        this.policyService=policyService;
    }

    public RateLimitCheckResponse checkRateLimit(RateLimitCheckRequest rateLimitCheckRequest){
        RateLimitPolicy rateLimitPolicy=policyService.getPolicy(rateLimitCheckRequest.getApiName());
        String key= rateLimitCheckRequest.getClientId()+":"+rateLimitCheckRequest.getApiName();
        long now=System.currentTimeMillis();
        int limit=rateLimitPolicy.getLimit();
        long windowSizeMs=rateLimitPolicy.getWindowSizeMs();


        FixedWindowCounter counter=requestCounters.get(key);

        if(counter==null){
            requestCounters.put(key,new FixedWindowCounter(1,now));

            return new RateLimitCheckResponse(
                    rateLimitCheckRequest.getAlgorithm(),
                    0,
                    limit-1,
                    true
            );
        }

        long elapsedTime=now-counter.getWindowStartTimeMS();

        if(elapsedTime<windowSizeMs) {
            if (counter.getRequestCount() < limit) {
                int curRequestCount = counter.increaseRequestCount();
                return new RateLimitCheckResponse(
                        rateLimitCheckRequest.getAlgorithm(),
                        0,
                        limit - curRequestCount,
                        true
                );
            }

            return new RateLimitCheckResponse(
                    rateLimitCheckRequest.getAlgorithm(),
                    windowSizeMs - elapsedTime,
                    limit - counter.getRequestCount(),
                    false
            );

        }
        else{
            counter.resetWindow(now);
            return new RateLimitCheckResponse(
                    rateLimitCheckRequest.getAlgorithm(),
                    0,
                    limit-1,
                    true
            );
        }



    };

}
