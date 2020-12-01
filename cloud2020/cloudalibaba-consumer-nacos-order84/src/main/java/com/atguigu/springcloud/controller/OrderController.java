package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author gaojunlong
 * @Date 2020-11-30
 * @Time 18:59
 * @Version 1.0
 */
@RestController
@Slf4j
public class OrderController {
    private static final String PAYMENT_URL = "http://nacos-payment-provider";

    @Value("${service-url.nacos-user-service}")
    private String serviceUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/get/{id}")
//    @SentinelResource(value = "fallback", fallback = "handleFallback") //fallback只处理业务异常
//    @SentinelResource(value = "fallback", blockHandler = "handleBlockHandler")
    @SentinelResource(value = "fallback", fallback = "handleFallback", blockHandler = "handleBlockHandler")
    public CommonResult getPayment(@PathVariable("id") Long id){
        if (id >= 4){
            throw new IllegalArgumentException("非法参数异常");
        }else {
            return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,
                    CommonResult.class);
        }
    }

    //兜底的方法  handleFallback
    public CommonResult handleFallback(@PathVariable("id") Long id, Throwable e){
        return new CommonResult(414, "==非法参数异常===", e);
    }

    //兜底的方法  handleBlockHandler
    public CommonResult handleBlockHandler(@PathVariable("id") Long id, BlockException e){
        return new CommonResult(414, "======非法参数异常=====", e);
    }

    //================OpenFeign===============
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSql(id);
    }
}
