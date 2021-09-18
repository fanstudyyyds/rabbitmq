package com.fan.springbootorderrabbitmqporducer.config;

/*
 * @author  fan
 * @date  2021/9/19 2:36
 * @aa  玉树临风美少年,揽镜自顾夜不眠
 */

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMqConfiguration {


    // 1： 声明交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_order_ex", true, false);
    }


    // 2: 声明队列 duanxin.fanout.queue
    @Bean
    public Queue duanxinqueue() {
        return new Queue("duanxin.fanout.queue", true);
    }


    // 2: 声明队列 duanxin.fanout.queue
    @Bean
    public Queue smsqueue() {
        return new Queue("sms.fanout.queue", true);
    }


    // 2: 声明队列 duanxin.fanout.queue
    @Bean
    public Queue emailqueue() {
        return new Queue("email.fanout.queue", true);
    }


    // 3: 确定绑定关系
    @Bean
    public Binding bindduanxin(){
        return BindingBuilder.bind(duanxinqueue()).to(fanoutExchange());
    }

    // 3: 确定绑定关系
    @Bean
    public Binding bindsms(){
        return BindingBuilder.bind(smsqueue()).to(fanoutExchange());
    }

    // 3: 确定绑定关系
    @Bean
    public Binding bindemail(){
        return BindingBuilder.bind(emailqueue()).to(fanoutExchange());
    }
}
