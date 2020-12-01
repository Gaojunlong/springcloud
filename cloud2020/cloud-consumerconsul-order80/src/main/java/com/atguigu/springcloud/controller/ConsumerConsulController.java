package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author gaojunlong
 * @Date 2020-11-24
 * @Time 20:16
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class ConsumerConsulController {
    public static final String INVOKE_URL = "http://consul-provider-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/consul")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/consul", String.class);
        return result;
    }
}
