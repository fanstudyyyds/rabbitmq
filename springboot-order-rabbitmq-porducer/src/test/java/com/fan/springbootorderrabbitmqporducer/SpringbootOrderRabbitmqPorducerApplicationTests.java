package com.fan.springbootorderrabbitmqporducer;

import com.fan.springbootorderrabbitmqporducer.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootOrderRabbitmqPorducerApplicationTests {

    @Autowired
    private OrderService orderService;


    @Test
    public void contextLoads_makeOrder_fanout() {
        orderService.makeOrder_fanout("100","100",10);
    }

    @Test
    public void contextLoads_makeOrder_direct() {
        orderService.makeOrder_direct("100","100",10);
    }

    @Test
    public void contextLoads_makeOrder_topic() {
        orderService.makeOrder_topic("100","100",10);
    }

    @Test
    public void contextLoads_makeOrder_ttl() {
        for (int i = 0; i < 20; i++) {
            orderService.makeOrder_ttl("100","100",10);

        }
    }

    @Test
    public void contextLoadsmakeOrder_ttl_MessagePostProcessor() {
        orderService.makeOrder_ttl_MessagePostProcessor("100","100",10);
    }

}
