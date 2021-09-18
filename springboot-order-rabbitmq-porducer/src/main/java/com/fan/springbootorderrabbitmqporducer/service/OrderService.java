package com.fan.springbootorderrabbitmqporducer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/*
 * @author  fan
 * @date  2021/9/19 2:32
 * @aa  玉树临风美少年,揽镜自顾夜不眠
 */
@Service
public class OrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;




/*
fanout
 */

//    public void makeOrder(String userId,String productId,int num){
//        // 1: 根据商品id查询库存是否充足
//        // 2: 保存订单
//        String orderId = UUID.randomUUID().toString();
//        System.out.println("保存订单成功：id是：" + orderId);
//        // 交换机
//        String exchangeName = "fanout_order_ex";
//        // 路由key
//        String routingKey = "";
//        // 3: 发送消息
//
//        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
//    }

   /*
   direct
    */
//    public void makeOrder(String userId,String productId,int num){
//        // 1: 根据商品id查询库存是否充足
//        // 2: 保存订单
//        String orderId = UUID.randomUUID().toString();
//        System.out.println("保存订单成功：id是：" + orderId);
//        // 交换机
//        String exchangeName = "direct_order_ex";
//        // 3: 发送消息
//        rabbitTemplate.convertAndSend(exchangeName,"email",orderId);
//        rabbitTemplate.convertAndSend(exchangeName,"sms",orderId);
//    }

    public void makeOrder(String userId,String productId,int num){
        // 1: 根据商品id查询库存是否充足
        // 2: 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("保存订单成功：id是：" + orderId);
        // 交换机
        String exchangeName = "topic_order_exchange";
        // 3: 发送消息
        rabbitTemplate.convertAndSend(exchangeName,"com.duanxin.xxx",orderId);
    }
}
