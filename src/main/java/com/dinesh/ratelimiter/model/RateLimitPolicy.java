package com.dinesh.ratelimiter.model;

import lombok.Getter;

@Getter
public class RateLimitPolicy {
    private String apiName;
    private int limit;
    private long windowSizeMs;
    private String algorithm;

    public RateLimitPolicy(String apiName, int limit, long windowSizeMs, String algorithm) {
        this.apiName = apiName;
        this.limit = limit;
        this.windowSizeMs = windowSizeMs;
        this.algorithm = algorithm;
    }
}
