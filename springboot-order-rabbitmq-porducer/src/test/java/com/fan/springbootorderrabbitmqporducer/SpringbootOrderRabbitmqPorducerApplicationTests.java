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
    public void contextLoads() {
        orderService.makeOrder("100","100",10);
    }

}
