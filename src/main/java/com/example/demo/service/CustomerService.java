package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Customers;

/**
 * 用户信息
 */
public interface CustomerService extends IService<Customers> {
    /**
     * 保存用户信息
     */
     void saveCustomer();

     String modifyStr(String str) throws InterruptedException;

     void saveBatchCustomer(int num);

}
