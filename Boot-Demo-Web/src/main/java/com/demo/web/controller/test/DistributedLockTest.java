package com.demo.web.controller.test;

import com.demo.manager.dtlock.RedisDistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("lockTest")
public class DistributedLockTest {

    @Autowired
    private RedisDistributedLock redisLock;

    private static final int THREAD_NUM = 100;
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
        String lockName = "cntLock";
        for(int i = 0 ; i < THREAD_NUM ; i++){
            new Thread(()->{
                try {
                    boolean locked = redisLock.addLock(lockName);
                    cntVal += 1;
                } finally {
                    boolean unlock = redisLock.unlock(lockName);
                    latch.countDown();
                }
            }).start();
        }

        latch.await();
        System.out.println("multi thread cnt result : " + cntVal);

    }

}
