package com.dinesh.ratelimiter.dto;

public class RateLimitCheckRequest {
    public String clientId;
    public String apiName;
    public String algorithm;

    public RateLimitCheckRequest(String clientId, String apiName, String algorithm) {
        this.clientId = clientId;
        this.apiName = apiName;
        this.algorithm = algorithm;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
