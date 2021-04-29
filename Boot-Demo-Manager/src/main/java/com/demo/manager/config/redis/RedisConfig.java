package com.demo.manager.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

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
public class RedisConfig {

    @Resource
    private ObjectMapper objectMapper;


    @Bean
    public DefaultRedisScript<Long> redisScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("scripts/checkAndDeleteKey.lua")));
        return redisScript;
    }

    @Bean
    public Jackson2HashMapper normalObjectMapper(){
        return new Jackson2HashMapper(objectMapper, false);
    }

    @Bean
    public RedisTemplate<String,Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 设置key序列化方式: key 使用string 序列化方式, value 使用json方式
        RedisSerializer stringSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        // 设置value序列化方式
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jsonRedisSerializer.setObjectMapper(new ObjectMapper());
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);

        return redisTemplate;
    }

}
