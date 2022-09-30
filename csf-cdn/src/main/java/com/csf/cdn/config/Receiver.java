package com.csf.cdn.config;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

//    @Autowired
//    private EmailService emailService;

    @KafkaListener(topics = "${spring.kafka.topic.userCreated}")
    public void receive(Object payload) {
        LOGGER.info("received payload='{}'", payload);
//        emailService.sendSimpleMessage(payload);
        System.out.println("Receive message");
        latch.countDown();
    }
}