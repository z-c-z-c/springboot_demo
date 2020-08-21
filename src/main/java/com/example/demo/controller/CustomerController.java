package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entity.Customers;
import com.example.demo.service.CustomerService;
import io.swagger.annotations.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

import static java.lang.Thread.sleep;

@RestController
@RequestMapping("/customer")
@Api(value = "customerController", description = "swagger测试")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    @ApiOperation(value = "保存客户信息")
    @ApiImplicitParam(name = "customers",value="Customers",required = true,dataType = "Customers")
    public R saveCustomer(@RequestBody Customers customers){
        customerService.saveCustomer();
        return R.ok();
    }

    @RabbitListener(queues = "MQ_QUEUE_NAME")
    public void getMQMessage(String message) throws InterruptedException {
        System.out.println("time:"+System.currentTimeMillis()+"jack接受到了了到消息:"+message);
    }


}
