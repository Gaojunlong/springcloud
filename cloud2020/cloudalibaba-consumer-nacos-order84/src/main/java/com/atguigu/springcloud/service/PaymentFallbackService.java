package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Author gaojunlong
 * @Date 2020-11-30
 * @Time 20:54
 * @Version 1.0
 */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult paymentSql(Long id) {
        return new CommonResult<>(444, "open-feign 整合 sentinel 实现的全局服务降级策略", null);
    }
}
