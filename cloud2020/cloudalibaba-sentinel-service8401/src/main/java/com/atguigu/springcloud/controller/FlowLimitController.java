package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.myhandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author gaojunlong
 * @Date 2020-11-30
 * @Time 14:26
 * @Version 1.0
 */
@RestController
@Slf4j
//@RequestMapping("/flow")
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "=======testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "========testB";
    }

    @GetMapping("/testD")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("test测试，RT");
        return "========testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        return "=======testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "==========deal_testHotKey,=====";
    }


}
