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
public class TTlRabbitMqConfiguration {


    // 1： 声明交换机
    @Bean
    public DirectExchange ttldirectExchange() {
        return new DirectExchange("ttl_direct_ex", true, false);
    }


    // 2: 声明队列 duanxin.fanout.queue
    @Bean
    public Queue directttlqueue() {
        Map<String, Object> args=new HashMap<>();
        args.put("x-message-ttl",5000);
        //超过的进入死信
        args.put("x-max-length",5);
        args.put("x-dead-letter-exchange","dead_direct_ex");
        args.put("x-dead-letter-routing-key","dead");
        return new Queue("ttl.direct.queue", true,false,false,args);
    }

    @Bean
    public Queue directttlMessageQueue() {
        return new Queue("ttl.Message.direct.queue", true);
    }

    // 3: 确定绑定关系
    @Bean
    public Binding ttlbindemail(){
        return BindingBuilder.bind(directttlqueue()).to(ttldirectExchange()).with("ttl");
    }

    @Bean
    public Binding ttlmsgbindemail(){
        return BindingBuilder.bind(directttlMessageQueue()).to(ttldirectExchange()).with("ttlmessage");
    }
}
