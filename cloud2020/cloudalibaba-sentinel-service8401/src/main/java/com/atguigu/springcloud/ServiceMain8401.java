package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author gaojunlong
 * @Date 2020-11-30
 * @Time 11:41
 * @Version 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@SpringBootApplication
public class ServiceMain8401 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMain8401.class, args);
    }
}
