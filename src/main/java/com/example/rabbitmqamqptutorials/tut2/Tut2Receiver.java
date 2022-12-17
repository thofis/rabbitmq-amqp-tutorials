package com.example.rabbitmqamqptutorials.tut2;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "hello")
@Slf4j
public class Tut2Receiver {

	private final int instance;

	private final Random random = new Random();

	public Tut2Receiver(int i) {
		instance = i;
	}

	@RabbitHandler
	public void receive(String incomingMessage) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		doWork(incomingMessage);
		watch.stop();
		log.info("instance {} [x] done in {} s", instance, watch.getTotalTimeSeconds());
	}

	private void doWork(String in) throws InterruptedException {
		// throw a exception randomly before finishing
		var number = random.nextInt();
		if (number != 1) {
			// message is requeued after exception
			//			throw new RuntimeException("Boom...");
			// message disappears from queue
			//			throw new AmqpRejectAndDontRequeueException("Boom...");
		}
		for (char ch : in.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
	}


}
