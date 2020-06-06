package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.CustomersDao;
import com.example.demo.entity.Customers;
import com.example.demo.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class CustomerServiceImpl extends ServiceImpl<CustomersDao,Customers> implements CustomerService {


    @Override
    public void saveCustomer() {
        Customers customers=new Customers();
        customers.setName("魏十二");
        customers.setAge(19);
        customers.setTelNum("124345312");
        customers.setSex("0");
        customers.setAddress("湖北荆州");
        customers.setCreateTime(new Date());
        customers.setModifyTime(new Date());
        this.save(customers);
        customers=new Customers();
        customers.setName("吴十三");
        this.save(customers);
    }
}
