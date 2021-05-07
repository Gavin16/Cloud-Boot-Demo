package com.demo.web.controller.test;

import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

@RestController
@RequestMapping("lockTest")
public class DistributedLockTest {

    @Autowired
    @Qualifier("zkDistributedLock")
    private Lock distributedLock;

    @Autowired
    @Qualifier("testLock")
    private RLock redissonLock;

    private static final int THREAD_NUM = 2000;
    private volatile int cntVal = 0;

    private CountDownLatch latch;

    /**
     * 测试方式：
     * (1) 同时启动100个线程,对某个volatile 变量进行计数, 查看是否会出现计数错误
     *
     */
    @GetMapping("testRedisLock")
    public void TestRedisDistLock() throws InterruptedException {
        cntVal = 0;
        latch = new CountDownLatch(THREAD_NUM);
        for(int i = 0 ; i < THREAD_NUM ; i++){
            new Thread(()->{
                try {
                    distributedLock.lock();
                    cntVal += 1;
                } finally {
                    distributedLock.unlock();
                    latch.countDown();
                }
            }).start();
        }

        latch.await();
        System.out.println("multi thread count result : " + cntVal);
    }


    @GetMapping("redissonLock")
    public void TestRedissionLock() throws InterruptedException {
        cntVal = 0;
        latch = new CountDownLatch(THREAD_NUM);
        for(int i = 0 ; i < THREAD_NUM ; i++){
            new Thread(()->{
                try {
                    redissonLock.lock();
                    cntVal += 1;
                } finally {
                    redissonLock.unlock();
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        System.out.println("multi thread count result : " + cntVal);
    }


}
