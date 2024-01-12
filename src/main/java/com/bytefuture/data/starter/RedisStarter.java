package com.bytefuture.data.starter;

import com.bytefuture.data.config.RedisConfig;
import com.bytefuture.data.common.service.RedisService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Administrator
 */
@EnableConfigurationProperties(value = { RedisConfig.class })
public class RedisStarter {

    @Bean
    public JedisObject initRedis(RedisConfig redisConfig) throws Exception {
        try {
            JedisObject object = RedisService.initRedis(redisConfig);
            return object;
        } catch (Exception e) {
            throw e;
        }
    }
}
