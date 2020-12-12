package com.demo.manager.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.Resource;


/**
 * (1) 为什么高阶API还需要配置
 * 若使用 redisTemplate 存Redis  key 和 Value 需要做序列化处理,否则redis中保存的数据中存在默认序列化引入的乱码
 * 使用 StringRedisTemplate 可以不对key做序列化，仅对value做序列化处理
 * [*] 此处配置向容器中加入自定义的对象，在使用时指定对象名即可
 *
 * (2) 是否可以直接在StringRedisTemplate 中配置
 *
 *
 */
@Configuration
public class GeneralRedisConfig {

    @Bean
    @Qualifier("generalRedisTemplate")
    public StringRedisTemplate getGeneralRedisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate srt = new StringRedisTemplate(factory);

        srt.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return srt;
    }

    @Resource
    private ObjectMapper objectMapper;

    @Bean
    @Qualifier("JSON2NormalHashMap")
    public Jackson2HashMapper normalObjectMapper(){
        return new Jackson2HashMapper(objectMapper, false);
    }
}
