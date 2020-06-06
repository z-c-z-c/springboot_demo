package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entity.Customers;
import com.example.demo.service.CustomerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
