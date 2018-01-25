package com.earthchen.seckill.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MQSenderTest {

    @Autowired
    private MQSender mqSender;


//    @Test
//    public void send() {
//        mqSender.send("mq测试");
//    }
//
//    @Test
//    public void sendMiaoshaMessage() {
//    }
//
//    @Test
//    public void sendTopic() {
//        mqSender.sendTopic("mq topic 方式测试");
//    }
//
//    @Test
//    public void sendFanout() {
//        mqSender.sendFanout("mq fanout模式(广播模式)");
//    }
//
//    @Test
//    public void sendHeader() {
//        mqSender.sendHeader("mq header模式");
//    }
}