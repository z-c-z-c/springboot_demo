package com.example.demo.controller;

import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class SyncController implements CommandLineRunner {

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
       customerService.saveBatchCustomer(1);

       System.out.println("1212121");
    }

    public String getStr() throws InterruptedException {
        String str="1234";
        str=str+"2345";
        str=str+customerService.modifyStr(str);
        System.out.println(str);
        return str;
    }
}
