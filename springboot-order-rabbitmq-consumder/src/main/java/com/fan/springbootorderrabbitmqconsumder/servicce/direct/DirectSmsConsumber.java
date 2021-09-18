package com.fan.springbootorderrabbitmqconsumder.servicce.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/*
 * @author  fan
 * @date  2021/9/19 2:58
 * @aa  玉树临风美少年,揽镜自顾夜不眠
 */
@Service
@RabbitListener(queues ={"sms.direct.queue"})
public class DirectSmsConsumber {
    // 告诉你的接收服务器的消息，没有返回值
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("sms--direct--->接收到订单消息，订单id是: " + message);
    }
}
