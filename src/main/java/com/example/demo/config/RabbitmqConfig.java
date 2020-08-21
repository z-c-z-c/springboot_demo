package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitmqConfig {
    private static final String MQ_QUEUE_NAME="MQ_QUEUE_NAME";

    @Bean
    public Queue createMqQueue(){
        return new Queue(MQ_QUEUE_NAME);
    }
}
