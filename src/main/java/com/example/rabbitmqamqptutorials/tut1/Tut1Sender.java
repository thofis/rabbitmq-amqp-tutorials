package com.example.rabbitmqamqptutorials.tut1;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
@Slf4j
public class Tut1Sender {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		String message = "Hello World";
		template.convertAndSend(queue.getName(), message);
		log.info(" [x] Sent '{}'", message);
	}
}
