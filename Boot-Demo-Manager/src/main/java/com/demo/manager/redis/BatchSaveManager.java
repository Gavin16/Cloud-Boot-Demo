package com.demo.manager.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 若向redis 存入50W 的数据, 考虑如何存入
 */
@Component
public class BatchSaveManager {

    @Autowired
    private RedisTemplate redisTemplate;

    public void bathSave(){

        Map<String,String> map = new HashMap<>();
        for(int i = 10_000; i < 60_000 ; i++){
            map.put(String.valueOf(i),"redisTest");
        }
        // 批量数据单独写和使用管道写，性能大约相差30倍
        // pipeline time cost :100565 ms
//        batchSaveUsePipeline(map);
        // separately time cost: 3667 s
        bathSaveSeparately(map);
    }

    private void bathSaveSeparately(Map<String, String> map) {
        Long start = System.currentTimeMillis();
        Set<String> keySet = map.keySet();
        for(String key : keySet){
            String val = map.get(key);
            redisTemplate.opsForValue().set(key,val);
        }
        System.out.println("separately time cost: " + (System.currentTimeMillis() - start) + " ms");
    }

    private void batchSaveUsePipeline(Map<String, String> map) {
        // 使用管道保存数据(内存数据)
        List list = redisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
            Long start = System.currentTimeMillis();
            for(Map.Entry<String,String> entry : map.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                redisConnection.set(key.getBytes(),value.getBytes());
            }
            System.out.println("pipeline time cost :" + (System.currentTimeMillis() - start) + " ms");
            return null;
        });
    }

}
