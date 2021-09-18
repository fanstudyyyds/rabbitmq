package com.fan.rabbitmq.service;

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

    // 交换机
    private String exchangeName = "fanout_order_ex";
    // 路由key
    private String routingKey = "";


    /**
     * @Author xuke
     * @Description 模拟用户购买商品下单的业务
     * @Date 22:26 2021/3/5
     * @Param [userId, productId, num]
     * @return void
     **/
    public void makeOrder(String userId,String productId,int num){
        // 1: 根据商品id查询库存是否充足
        // 2: 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("保存订单成功：id是：" + orderId);
        // 3: 发送消息
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }
}
