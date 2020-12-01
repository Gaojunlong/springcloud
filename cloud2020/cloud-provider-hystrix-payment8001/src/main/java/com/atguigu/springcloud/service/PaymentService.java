package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author gaojunlong
 * @Date 2020-11-25
 * @Time 18:04
 * @Version 1.0
 */
@Service
public class PaymentService {
    /**
     * 正常运行
     * @param id
     * @return
     */
    public String payment_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+
                " paymentInfo_Ok,id: "+id+"\t"+"hhhh";
    }
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties =
            {
                    //设置峰值，超过 3 秒，就会调用兜底方法，这个时间也可以由feign控制
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            })
    public String paymentInfo_Timeout(Integer id){
//        int age = 10 / 0;
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+
                "paymentInfo_Ok,id: "+id+"\t"+"hhhh"+"，耗时s:";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+
                "paymentInfo_TimeOutHandler,系统繁忙，运行报错！,id: "+id+"\t"+"/(ㄒoㄒ)/~~";
    }

    //==============服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled", value="true"),  // 是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),  //请求次数(10次)
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000"), // 时间窗口期（10s）
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="60"),  // 失败率达到多少后跳闸（60%）
            //整体意思：10秒内 10次请求，有6次失败，就跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("======id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "=======id不能为负数，请稍后再试，id：" +id;
    }
}
