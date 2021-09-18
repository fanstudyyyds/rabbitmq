package com.fan.rabbitmq.routing;

/*
 * @author  fan
 * @date  2021/9/17 15:49
 * @aa  玉树临风美少年,揽镜自顾夜不眠
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 发布订阅模式的具体实现
 类型：fanout
 特点：Fanout - 发布与订阅模式，是一种广播机制，它是没有路由 key的模式
 */
public class Producer {
    public static void main(String[] args) {
        // 1: 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2: 设置连接属性
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = null;
        Channel channel = null;
        try {
            // 3: 从连接工厂中获取连接
            connection = connectionFactory.newConnection("生产者");
            // 4: 从连接中获取通道channel
            channel = connection.createChannel();

            // 5： 准备发送消息的内容
            String message = "hello xuexi!!!";

            // 6：准备交换机
            String exchangeName = "fanout_change";

            // 8: 指定交换机的类型
            String type = "fanout";
            // 7: 发送消息给中间件rabbitmq-server
            // @params1: 交换机exchange
            // @params2: 队列名称/routingkey
            // @params3: 属性配置
            // @params4: 发送消息的内容
            //  #.course.* queue3
            // *.order.# queue2 ta
            // com.order.course.xxx collecion
            channel.basicPublish(exchangeName,"", null, message.getBytes());


            System.out.println("消息发送成功!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("发送消息出现异常...");
        } finally {

            // 7: 释放连接关闭通道
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
