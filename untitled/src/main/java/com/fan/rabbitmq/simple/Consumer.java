package com.fan.rabbitmq.simple;

/*
 * @author  fan
 * @date  2021/9/17 15:06
 * @aa  玉树临风美少年,揽镜自顾夜不眠
 */

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {


    public static void main(String[] args) {

        // 所有的中间件技术都是基于tcp/ip协议基础之上构建新型的协议规范，只不过rabbitmq遵循的是amqp
        // ip port

        // 1: 创建连接工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");//服务器IP
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = null;
        Channel channel = null;
        try {
            // 2: 创建连接Connection
            connection = connectionFactory.newConnection("消费者");
            // 3: 通过连接获取通道Channel
            channel = connection.createChannel();
            // 4: 通过通创建交换机，声明队列，绑定关系，路由key,发送消息，和接收消息


            // true = ack 正常的逻辑是没问题 死循环 rabbit 重发策略
            // false = nack 消息这在消费消息的时候可能会异常和故障
            final  Channel channel2 = channel;
            channel2.basicConsume("queue1", false, new DeliverCallback() {
                public void handle(String consumerTag, Delivery message) throws IOException {
                    try {
                        System.out.println("收到消息是" + new String(message.getBody(), "UTF-8"));
                        channel2.basicAck(message.getEnvelope().getDeliveryTag(),false);
                    }catch (Exception ex){
                        ex.printStackTrace();
                        // 三次确认 -- reject + sixin
                    }

                }
            }, new CancelCallback() {
                public void handle(String consumerTag) throws IOException {
                    System.out.println("接受失败了...");
                }
            });

            System.out.println("开始接受消息");
            System.in.read();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // 7: 关闭通道
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 8: 关闭连接

            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }


    }
}
