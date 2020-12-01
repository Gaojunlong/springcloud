package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author gaojunlong
 * @Date 2020-11-25
 * @Time 19:15
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "paymentTimeoutMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.payment_OK(id);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand
//    @HystrixCommand(
//            fallbackMethod = "paymentTimeoutFallbackMethod",
//            commandProperties = {
//                    //设置峰值，超过 1.5 秒，就会调用兜底方法
//                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//            })
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        int age = 10/0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }

    //兜底的方法
    public String paymentTimeoutFallbackMethod(@PathVariable("id") Integer id){
        return "消费者80，访问超时，失败！！！";
    }

    public String paymentTimeoutMethod(){
        return "Global 报错";
    }
}
