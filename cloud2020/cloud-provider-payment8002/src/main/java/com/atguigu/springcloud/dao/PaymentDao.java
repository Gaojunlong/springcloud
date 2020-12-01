package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author gaojunlong
 * @Date 2020-11-23
 * @Time 9:37
 * @Version 1.0
 */
@Mapper
public interface PaymentDao {

    //新增
    public int create(Payment payment);

    //查找
    public Payment getPaymentById(@Param("id") Long id);
}
