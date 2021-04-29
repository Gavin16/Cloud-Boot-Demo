package com.demo.manager.dtlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Redis 实现分布式锁
 *
 * 基本实现方式 setnx 若返回 1 则代表加锁成功，若返回0 则加锁失败
 * 锁的基本功能
 * 版本1：
 * (1) 要能自动过期，以防调用者线程异常终止后，锁无法释放
 * (2) 加锁线程和解锁线程要求是同一个线程，否则无法解锁
 * (3) 锁设计成阻塞的，具体等待逻辑留给应用端实现
 * (4) 锁需要是重入的，否则自己线程再次加锁会锁住自己
 *
 */
@Component
public class RedisDistributedLock {

    @Autowired
    private RedisTemplate redisTemplate;

    // 超时时长设置为 5秒
    private static final int expireTm = 5;

    private AtomicReference<String> holderUUID = new AtomicReference<>();

    public boolean addLock(String lockName){
        // 当前业务名作为key; 临时生成的UUID 作为value
        String value = UUID.randomUUID().toString();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        for(boolean isOK = false ; !isOK ;){
            isOK = valueOperations.setIfAbsent(lockName, value, expireTm, TimeUnit.SECONDS);
        }
        holderUUID.set(value);
        return true;
    }


    // 释放锁
    public boolean unlock(String lockName){
        Object value = redisTemplate.opsForValue().get(lockName);
        if(Objects.nonNull(value)){
            String strVal = (String) value;
            String uuid = holderUUID.get();
            if(!strVal.equals(uuid)){
                return false;
            }
        }
        redisTemplate.delete(lockName);
        holderUUID.set(null);
        return true;
    }
}
