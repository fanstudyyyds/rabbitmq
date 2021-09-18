package com.fan.springbootorderrabbitmqconsumder.config;

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

@Configuration
public class DirectRabbitMqConfiguration {


    // 1： 声明交换机
    @Bean
    public DirectExchange fanoutExchange() {
        return new DirectExchange("direct_order_ex", true, false);
    }


    // 2: 声明队列 duanxin.fanout.queue
    @Bean
    public Queue duanxinqueue() {
        return new Queue("duanxin.direct.queue", true);
    }


    // 2: 声明队列 duanxin.fanout.queue
    @Bean
    public Queue smsqueue() {
        return new Queue("sms.direct.queue", true);
    }


    // 2: 声明队列 duanxin.fanout.queue
    @Bean
    public Queue emailqueue() {
        return new Queue("email.direct.queue", true);
    }


    // 3: 确定绑定关系
    @Bean
    public Binding bindduanxin(){
        return BindingBuilder.bind(duanxinqueue()).to(fanoutExchange()).with("duanxin");
    }

    // 3: 确定绑定关系
    @Bean
    public Binding bindsms(){
        return BindingBuilder.bind(smsqueue()).to(fanoutExchange()).with("sms");
    }

    // 3: 确定绑定关系
    @Bean
    public Binding bindemail(){
        return BindingBuilder.bind(emailqueue()).to(fanoutExchange()).with("email");
    }
}
