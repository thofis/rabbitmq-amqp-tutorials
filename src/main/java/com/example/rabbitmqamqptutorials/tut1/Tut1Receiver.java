package com.example.rabbitmqamqptutorials.tut1;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
@Slf4j
public class Tut1Receiver {
	@RabbitHandler
	public void receive(String incomingMessage) {
		log.info(" [x] received '{}'", incomingMessage);
	}
}
