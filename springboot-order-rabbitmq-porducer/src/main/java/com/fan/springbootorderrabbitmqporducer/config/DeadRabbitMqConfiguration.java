package com.fan.springbootorderrabbitmqporducer.config;

/*
 * @author  fan
 * @date  2021/9/19 2:36
 * @aa  玉树临风美少年,揽镜自顾夜不眠
 */

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadRabbitMqConfiguration {


    // 1： 声明交换机
    @Bean
    public DirectExchange deaddirect() {
        return new DirectExchange("dead_direct_ex", true, false);
    }


    // 2: 声明队列 duanxin.fanout.queue
    @Bean
    public Queue deadqueue() {
        return new Queue("dead.direct.queue", true);
    }
    @Bean
    public Binding deadbinds(){
        return BindingBuilder.bind(deadqueue()).to(deaddirect()).with("dead");
    }
}
