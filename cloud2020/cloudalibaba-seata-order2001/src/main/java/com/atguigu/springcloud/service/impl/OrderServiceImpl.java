package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author gaojunlong
 * @Date 2020-12-01
 * @Time 16:09
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    public void create(Order order) {
        //1、新建订单
        log.info("---->开始新建订单");
        orderDao.create(order);

        //2、扣减库存
        log.info("---->订单微服务开始调用库存，做扣减");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("---->订单微服务开始调用库存，做扣减end===");

        //3、扣减账户
        log.info("---->订单微服务开始调用账户，做扣减money");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("---->订单微服务开始调用账户，做扣减money===end");

        //4、修改订单状态，从0~1，1表示完成
        log.info("---->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("------->修改订单状态结束");

        log.info("------>下订单结束，=====>");
    }
}
