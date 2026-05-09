package com.dinesh.ratelimiter.service;

import com.dinesh.ratelimiter.model.FixedWindowCounter;
import com.dinesh.ratelimiter.dto.RateLimitCheckRequest;
import com.dinesh.ratelimiter.dto.RateLimitCheckResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RateLimitService {
    private final Map<String,FixedWindowCounter> requestCounters=new HashMap<>();

    private static final int LIMIT=10;
    private static final long FIXED_WINDOW=60_000;

    public RateLimitCheckResponse checkRateLimit(RateLimitCheckRequest rateLimitCheckRequest){
        String key= rateLimitCheckRequest.getClientId()+":"+rateLimitCheckRequest.getApiName();
        long now=System.currentTimeMillis();

        FixedWindowCounter counter=requestCounters.get(key);

        if(counter==null){
            requestCounters.put(key,new FixedWindowCounter(1,now));

            return new RateLimitCheckResponse(
                    rateLimitCheckRequest.getAlgorithm(),
                    0,
                    LIMIT-1,
                    true
            );
        }

        long elapsedTime=now-counter.getWindowStartTimeMS();

        if(elapsedTime<FIXED_WINDOW) {
            if (counter.getRequestCount() < LIMIT) {
                int curRequestCount = counter.increaseRequestCount();
                return new RateLimitCheckResponse(
                        rateLimitCheckRequest.getAlgorithm(),
                        0,
                        LIMIT - curRequestCount,
                        true
                );
            }

            return new RateLimitCheckResponse(
                    rateLimitCheckRequest.getAlgorithm(),
                    FIXED_WINDOW - elapsedTime,
                    LIMIT - counter.getRequestCount(),
                    false
            );

        }
        else{
            counter.resetWindow(now);
            return new RateLimitCheckResponse(
                    rateLimitCheckRequest.getAlgorithm(),
                    0,
                    LIMIT-1,
                    true
            );
        }



    };

}
