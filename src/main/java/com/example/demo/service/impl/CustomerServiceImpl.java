package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.CustomersDao;
import com.example.demo.entity.Customers;
import com.example.demo.service.CustomerService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.tomcat.util.http.FastHttpDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class CustomerServiceImpl extends ServiceImpl<CustomersDao, Customers> implements CustomerService {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void saveCustomer() {
        Customers customers = new Customers();
        customers.setName("魏十二");
        customers.setAge(19);
        customers.setTelNum("124345312");
        customers.setSex("0");
        customers.setAddress("湖北荆州");
        customers.setCreateTime(new Date());
        customers.setModifyTime(new Date());
        this.save(customers);
        customers = new Customers();
        customers.setName("吴十三");
        this.save(customers);
    }
    FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    @Async
    @Override
    public String modifyStr(String str) throws InterruptedException {
        System.out.println("异步执行开始。。。。。。");
        Thread.sleep(10000);
        str = str + "holle world";
        System.out.println("异步执行结果->" + str);
        return str;
    }

    @Override
    public void saveBatchCustomer(int num) {
        System.out.println("第"+num+"次调用");
        ThreadGroup currentGroup =Thread.currentThread().getThreadGroup();
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);
        for (int i = 0; i < noThreads; i++){
            if("test-0".equals(lstThreads[i].getName())){
                lstThreads[i].stop();
                System.out.println("线程号：" + i + " = " + lstThreads[i].getName()+"被中断，num="+num);
            }
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("启用"+num+"次线程");
                List<Customers> customersList = new ArrayList<>(16);
                Customers customers;
                for (int i = 0; i < 500; i++) {
                    customers = new Customers();
                    customers.setName(String.valueOf(i));
                    customers.setAge(19);
                    customers.setTelNum("124345312");
                    customers.setSex("0");
                    customers.setAddress("湖北荆州");
                    customers.setCreateTime(new Date());
                    customers.setModifyTime(new Date());
                    customersList.add(customers);
                }
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                        try {
                            System.out.println("thread"+num+".starttime------>" + fastDateFormat.format(new Date()));
                            System.out.println(customersList.size());
                            saveBatch(customersList,1000);
                            System.out.println("thread"+num+".endtime------>" + fastDateFormat.format(new Date()));
                        } catch (Exception e) {
                            transactionStatus.setRollbackOnly();
                        }

                    }

                });

            }
        });

        thread.setName("test-0");
        thread.start();
    }

}
