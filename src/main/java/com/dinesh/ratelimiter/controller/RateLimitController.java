package com.dinesh.ratelimiter.controller;

import com.dinesh.ratelimiter.dto.RateLimitCheckRequest;
import com.dinesh.ratelimiter.dto.RateLimitCheckResponse;
import com.dinesh.ratelimiter.service.RateLimitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rate-limit")
public class RateLimitController {

    private final RateLimitService rateLimitService;

    public RateLimitController(RateLimitService rateLimitService) {
        this.rateLimitService = rateLimitService;
    }

    @PostMapping("/check")
    public ResponseEntity<RateLimitCheckResponse> checkRateLimit(@RequestBody RateLimitCheckRequest rateLimitCheckRequest){
        RateLimitCheckResponse rateLimitCheckResponse = rateLimitService.checkRateLimit(rateLimitCheckRequest);

        return ResponseEntity
                .status(rateLimitCheckResponse.getAllowed() ? 200 : 429)
                .body(rateLimitCheckResponse);
    }

}
