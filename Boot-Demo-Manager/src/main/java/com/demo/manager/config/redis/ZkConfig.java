package com.demo.manager.config.redis;

import lombok.Data;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@PropertySource("classpath:/application-zk.properties")
@Configuration
public class ZkConfig {

    @Value("${zk.address}")
    private String address;

    private static final String Z_NODE = "/ZK_LOCK";

    @Bean
    public ZkClient getZkClient(){
        ZkClient zkClient = new ZkClient(address);
        if(!zkClient.exists(Z_NODE)){
            zkClient.createPersistent(Z_NODE);
        }
        return zkClient;
    }
}
