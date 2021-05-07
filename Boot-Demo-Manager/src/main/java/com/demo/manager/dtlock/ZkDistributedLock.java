package com.demo.manager.dtlock;

import com.demo.manager.config.redis.ZkConfig;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 分布式锁的ZooKeeper 实现
 *
 * 实现分布式锁用到的zk的特性
 * (1) zk的节点不能重复创建
 * (2) zk已创建的临时节点在客户端(线程) 失去连接时会自动被清除
 * (3) zk提供了watch机制,当被watch的节点发生变化(修改,删除)时，能通知指定的客户端
 * (4) zk提供了创建顺序节点的功能，这样可以通过后面节点监听前一个节点的事件来避免通知大量客户端
 *
 */
@Component
public class ZkDistributedLock implements Lock {

    @Autowired
    private ZkClient zkClient;

    private static final String Z_NODE = "/ZK_LOCK";

    private static CountDownLatch cdl = new CountDownLatch(1);

    private ConcurrentHashMap<String,String> currentPathMap = new ConcurrentHashMap();
    private ConcurrentHashMap<String,String> precedingPathMap = new ConcurrentHashMap();

    @Override
    public void lock() {
        while(!tryLock()){
            // 尝试加锁 进入等待 监听
            waitForLock();
        }
        System.out.println("加锁成功!");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        String threadName = Thread.currentThread().getName();
        // 第一次就进来创建自己的临时节点
        if (!currentPathMap.containsKey(threadName)) {
            String path = zkClient.createEphemeralSequential(Z_NODE + "/", "lock");
            currentPathMap.put(threadName,path);
        }
        String nodePath = currentPathMap.get(threadName);

        // 对节点排序
        List<String> children = zkClient.getChildren(Z_NODE);
        Collections.sort(children);

        // 当前的是最小节点就返回加锁成功
        if (nodePath.equals(Z_NODE + "/" + children.get(0))) {
            System.out.println("try lock success..");
            return true;
        } else {
            // 不是最小节点 就找到自己的前一个 依次类推 释放也是一样
            int i = Collections.binarySearch(children, nodePath.substring(Z_NODE.length() + 1));
            String beforePath = Z_NODE + "/" + children.get(i - 1);
            precedingPathMap.put(threadName,beforePath);
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        String name = Thread.currentThread().getName();
        String path = currentPathMap.get(name);
        zkClient.delete(path);
        currentPathMap.remove(name);
        precedingPathMap.remove(name);
    }

    /**
     * 多个线程加锁失败时, 都会被latch 阻塞，直到zk节点有被删除，对应latch 减1
     * 这时将触发所有线程从阻塞中唤醒，并同时尝试获取锁, 这些线程中仅最靠前的节点能获取到，其它线程又将进入阻塞状态
     */
    public void waitForLock() {
        IZkDataListener listener = new IZkDataListener() {
            public void handleDataChange(String s, Object o){}
            public void handleDataDeleted(String s){
                System.out.println(Thread.currentThread().getName() + ":监听到节点删除事件！-------------------");
                cdl.countDown();
            }
        };
        // 监听
        String name = Thread.currentThread().getName();
        String beforePath = precedingPathMap.get(name);
        this.zkClient.subscribeDataChanges(beforePath, listener);
        if (zkClient.exists(beforePath)) {
            try {
                System.out.println("加锁失败 等待");
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 释放监听
        zkClient.unsubscribeDataChanges(beforePath, listener);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
