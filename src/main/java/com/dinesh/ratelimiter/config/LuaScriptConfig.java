package com.dinesh.ratelimiter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.List;

@Configuration
public class LuaScriptConfig {

    @Bean
    public DefaultRedisScript<List> fixedWindowRedisScript(){
        DefaultRedisScript<List> script =new DefaultRedisScript<>();
        script.setLocation(
                new ClassPathResource(
                    "scripts/fixed-window-rate-limiter.lua"
            )
        );

        script.setResultType(List.class);
        return script;
    }

}
