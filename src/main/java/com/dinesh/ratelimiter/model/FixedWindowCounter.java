package com.dinesh.ratelimiter.model;

import lombok.Getter;

public class FixedWindowCounter {
    @Getter
    private int requestCount;
    @Getter
    private long windowStartTimeMS;

    public FixedWindowCounter(int requestCount,long windowStartTimeMS){
        this.requestCount=requestCount;
        this.windowStartTimeMS=windowStartTimeMS;
    }

    public int increaseRequestCount() {
        this.requestCount ++;
        return this.requestCount;
    }

    public void resetWindow(long windowStartTimeMS){
        this.requestCount=1;
        this.windowStartTimeMS=windowStartTimeMS;
    }
}
