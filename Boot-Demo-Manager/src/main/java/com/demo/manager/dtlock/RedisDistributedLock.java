package com.demo.manager.dtlock;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Redis 实现分布式锁
 *
 * 基本实现方式 setnx 若返回 1 则代表加锁成功，若返回0 则加锁失败
 * 锁的基本功能
 * 版本1：
 * (1) 要能自动过期，以防调用者线程异常终止后，锁无法释放
 * (2) 加锁线程和解锁线程要求是同一个线程，否则无法解锁
 * (3) 锁设计成阻塞的，采用自旋方式阻塞
 *
 *
 * 版本2：
 * 在版本1的基础上增加：释放锁操作改为 lua 脚本封装的原子操作
 *
 *
 */
@Component
public class RedisDistributedLock {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DefaultRedisScript<Long> redisScript;

    // 超时时长设置为 5秒
    private static final int expireTm = 10;

    Map<String,String> threadUUIDMap = new ConcurrentHashMap<>();


    public boolean addLock(String lockName){
        // 当前业务名作为key; 临时生成的UUID 作为value
        String value = UUID.randomUUID().toString();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        for(boolean isOK = false ; !isOK ;){
            isOK = valueOperations.setIfAbsent(lockName, value, expireTm, TimeUnit.SECONDS);
        }
        threadUUIDMap.put(Thread.currentThread().getName(),value);
        return true;
    }


    /**
     * 释放锁时使用lua 脚本将 线程判断和k-v删除封装为原子操作后,锁正常工作且性能提升
     * @param lockName
     * @return
     */
    public boolean unlock(String lockName){
        String threadName = Thread.currentThread().getName();
        String uuid = threadUUIDMap.get(threadName);
        // 执行删除结果
        Object execute = redisTemplate.execute(redisScript, Lists.newArrayList(lockName), uuid);
        if(Objects.nonNull(execute)){
            Long aLong = Long.valueOf(String.valueOf(execute));
            // 删除成功返回1,失败返回0
            if(aLong < 1){
                return false;
            }
        }
        threadUUIDMap.remove(threadName);
        return true;
    }
}
