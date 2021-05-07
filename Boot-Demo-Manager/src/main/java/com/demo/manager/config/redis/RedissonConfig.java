package com.demo.manager.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application-redis.properties")
public class RedissonConfig {

    @Value("${redis.address}")
    private String address;

    @Value("${redis.password}")
    private String password;

    @Bean
    @Qualifier("testLock")
    public RLock getRedissonLock(){
        String lockName = "testLock";
        Config config = new Config();
        config.useSingleServer().setAddress(address).setPassword(password);
        RedissonClient redissonClient = Redisson.create(config);
        RLock lock = redissonClient.getLock(lockName);
        return lock;
    }
}
