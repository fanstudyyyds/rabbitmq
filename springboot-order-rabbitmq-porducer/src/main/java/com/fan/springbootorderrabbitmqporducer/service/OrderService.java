package com.fan.springbootorderrabbitmqporducer.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
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


    /**
     * fanout
     */

    public void makeOrder_fanout(String userId, String productId, int num) {
        // 1: 根据商品id查询库存是否充足
        // 2: 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("保存订单成功：id是：" + orderId);
        // 交换机
        String exchangeName = "fanout_order_ex";
        // 路由key
        String routingKey = "";
        // 3: 发送消息

        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);
    }

    /**
     * direct
     */
    public void makeOrder_direct(String userId, String productId, int num) {
        // 1: 根据商品id查询库存是否充足
        // 2: 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("保存订单成功：id是：" + orderId);
        // 交换机
        String exchangeName = "direct_order_ex";
        // 3: 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "email", orderId);
        rabbitTemplate.convertAndSend(exchangeName, "sms", orderId);
    }


    /**
     * topic
     *
     * @param userId
     * @param productId
     * @param num
     */
    public void makeOrder_topic(String userId, String productId, int num) {
        // 1: 根据商品id查询库存是否充足
        // 2: 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("保存订单成功：id是：" + orderId);
        // 交换机
        String exchangeName = "topic_order_exchange";
        // 3: 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "com.duanxin.xxx", orderId);
    }

    /**
     * ttl
     *
     * @param userId
     * @param productId
     * @param num
     */
    public void makeOrder_ttl(String userId, String productId, int num) {
        // 1: 根据商品id查询库存是否充足
        // 2: 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("保存订单成功：id是：" + orderId);
        // 交换机
        String exchangeName = "ttl_direct_ex";
        // 3: 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "ttl", orderId);
    }

    /**
     * ttl
     *
     * @param userId
     * @param productId
     * @param num
     */
    public void makeOrder_ttl_MessagePostProcessor(String userId, String productId, int num) {
        // 1: 根据商品id查询库存是否充足
        // 2: 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("保存订单成功：id是：" + orderId);
        // 交换机
        String exchangeName = "ttl_direct_ex";
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            public Message postProcessMessage(Message message) {
                //这里就是字符串
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        // 3: 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "ttlmessage", orderId, messagePostProcessor);
    }
}
