package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * @Author gaojunlong
 * @Date 2020-11-23
 * @Time 10:00
 * @Version 1.0
 */
public interface PaymentService {
    //新增
    public int create(Payment payment);

    //查找
    public Payment getPaymentById(Long id);
}
