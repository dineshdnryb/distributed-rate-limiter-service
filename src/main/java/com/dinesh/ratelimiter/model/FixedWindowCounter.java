package com.dinesh.ratelimiter.model;

import lombok.Getter;

@Getter
public class FixedWindowCounter {
    private int requestCount;
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
