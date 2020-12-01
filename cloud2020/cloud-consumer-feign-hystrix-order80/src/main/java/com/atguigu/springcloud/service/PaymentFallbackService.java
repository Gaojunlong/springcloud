package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author gaojunlong
 * @Date 2020-11-26
 * @Time 9:14
 * @Version 1.0
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String payment_OK(Integer id) {
        return "PaymentFallbackService======payment_OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallbackService======paymentInfo_Timeout";
    }
}
