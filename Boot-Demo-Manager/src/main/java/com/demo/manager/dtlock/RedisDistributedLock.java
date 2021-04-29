package com.demo.manager.dtlock;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.*;
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
 * 版本3:
 * 增加子线程定时延长过期时间 -- 如果创建子线程的父线程挂了，子线程沦为孤儿线程会怎么样？
 *
 */
@Component
public class RedisDistributedLock {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    @Qualifier("checkAndDeleteKey")
    private DefaultRedisScript<Long> delScript;

    @Autowired
    @Qualifier("prolongKeyExpireTime")
    private DefaultRedisScript<Long> prolongScript;


    private ThreadLocal<SubDaemonThread> threadLocal = new ThreadLocal<>();

    // 超时时长设置为 5秒
    private static final int expireTm = 3;

    Map<String,String> threadUUIDMap = new ConcurrentHashMap<>();


    public boolean addLock(String lockName){
        // 当前业务名作为key; 临时生成的UUID 作为value
        String threadName = Thread.currentThread().getName();
        String value = UUID.randomUUID().toString();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        for(boolean isOK = false ; !isOK ;){
            isOK = valueOperations.setIfAbsent(lockName, value, expireTm, TimeUnit.SECONDS);
        }
        // 设置守护线程延长分布式锁过期时间, 每隔 expireTm/3 时长执行一次
        addDaemonProlongThread(lockName,value,expireTm);

        threadUUIDMap.put(threadName,value);
        return true;
    }

    /**
     * 设计前明确
     * (1) 创建守护线程结束后，守护线程是否也会结束执行 -- 不会结束执行
     * (2) 守护线程怎么定时执行 -- 使用TimeUnit 对应单位的sleep() 方法可实现
     * (3) 怎么通知守护线程结束执行 -- 自定义子线程，内部设置volatile 状态标识，并在快结束执行时设置该标识
     * (4) 啥时候结束守护线程的执行 -- 线程执行完之后，释放锁之后
     */
    private void addDaemonProlongThread(String lockName,String uuid,int expireTm) {
        SubDaemonThread subDaemonThread = new SubDaemonThread(redisTemplate, prolongScript, lockName, uuid);
        subDaemonThread.setDaemon(true);
        // 存入ThreadLocal , 释放锁时从ThreadLocal 取出并结束线程执行
        threadLocal.set(subDaemonThread);

        subDaemonThread.start();
    }


    static class SubDaemonThread extends Thread{
        private RedisTemplate<String,Object> redisTemplate;
        private DefaultRedisScript<Long> prolongScript;
        private String lockName;
        private String uuid;
        private volatile boolean stopped = false;

        public void setStopped(){
            this.stopped = true;
        }

        public SubDaemonThread(RedisTemplate<String,Object> redisTemplate,
                             DefaultRedisScript<Long> prolongScript,String lockName,String uuid){
            this.redisTemplate =  redisTemplate;
            this.prolongScript = prolongScript;
            this.lockName = lockName;
            this.uuid = uuid;
        }

        @Override
        public void run() {
            int period = (expireTm / 3 <= 0 ) ? 1 : expireTm/3;
            while(!stopped){
                try {
                    // 延长锁之前先判断当前value 是否与当前线程uuid 相同
                    // 相同则延长当前k-v 过期时间至 expireTm
                    Object execute = redisTemplate.execute(prolongScript, Lists.newArrayList(lockName), uuid,expireTm);
                    if(Objects.nonNull(execute)){
                        System.out.println("thead :" + Thread.currentThread().getName() + ", prolong result : " + execute);
                        Long res = (Long) execute;
                        if(res == 1){
                            System.out.println("expire time prolonged to " + expireTm + " seconds");
                        }
                    }
                    TimeUnit.SECONDS.sleep(period);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
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
        Object execute = redisTemplate.execute(delScript, Lists.newArrayList(lockName), uuid);
        if(Objects.nonNull(execute)){
            Long aLong = Long.valueOf(String.valueOf(execute));
            // 删除成功返回1,失败返回0
            if(aLong < 1){
                return false;
            }
        }
        threadUUIDMap.remove(threadName);
        SubDaemonThread thread = threadLocal.get();
        if(Objects.nonNull(thread)){
            thread.setStopped();
            threadLocal.remove();
        }
        return true;
    }
}
