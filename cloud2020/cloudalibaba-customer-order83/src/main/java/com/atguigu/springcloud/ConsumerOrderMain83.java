package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author gaojunlong
 * @Date 2020-11-28
 * @Time 11:03
 * @Version 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class ConsumerOrderMain83 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain83.class, args);
    }
}
