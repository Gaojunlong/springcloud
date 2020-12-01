package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author gaojunlong
 * @Date 2020-12-01
 * @Time 15:43
 * @Version 1.0
 */
@Mapper
public interface OrderDao {

    //1、新建订单
    void create(Order order);

    //2、修改订单状态，改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
