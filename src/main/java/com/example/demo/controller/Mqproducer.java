package com.example.demo.controller;

import com.example.demo.service.CustomerService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author zc
 */
@Component
public class Mqproducer implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        customerService.saveBatchCustomer(2);
        //测试idea中git合并
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("hyn-demo-pool-%d").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(9,
                20,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        for(int i=0;i<1;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    rabbitTemplate.convertAndSend("MQ_QUEUE_NAME",Thread.currentThread().getName()+"测试mq消息发送！！！！！");
                }
            });
        }
        executor.shutdown();

    }
}
