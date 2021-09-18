package com.fan.springbootorderrabbitmqconsumder.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/*
 * @author  fan
 * @date  2021/9/19 2:58
 * @aa  玉树临风美少年,揽镜自顾夜不眠
 */
@Service
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "sms.topic.queue",durable = "true",autoDelete="false"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "com.#"
))
public class TopicSmsConsumber {
    // 告诉你的接收服务器的消息，没有返回值
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("sms--direct--->接收到订单消息，订单id是: " + message);
    }
}
