package com.dinesh.ratelimiter.dto;

public class RateLimitCheckResponse {

    public Boolean allowed;
    public Integer remaining;
    public long retryAfterMs;
    public String algorithm;

    public RateLimitCheckResponse(String algorithm, long retryAfterMs, Integer remaining, Boolean allowed) {
        this.algorithm = algorithm;
        this.retryAfterMs = retryAfterMs;
        this.remaining = remaining;
        this.allowed = allowed;
    }

    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public long getRetryAfterMs() {
        return retryAfterMs;
    }

    public void setRetryAfterMs(Integer retryAfterMs) {
        this.retryAfterMs = retryAfterMs;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
