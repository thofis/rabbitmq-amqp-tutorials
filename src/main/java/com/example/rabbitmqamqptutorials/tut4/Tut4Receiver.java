package com.example.rabbitmqamqptutorials.tut4;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@Slf4j
public class Tut4Receiver {

	@RabbitListener(queues = "#{autoDeleteQueue1.name}")
	public void receive1(Message message, String in) throws InterruptedException {
		receive(in, 1, message.getMessageProperties().getReceivedRoutingKey());
	}
//	@RabbitListener(queues = "#{autoDeleteQueue1.name}")
//	public void receive1b(Message message, String in) throws InterruptedException {
//		receive(in, 3, message.getMessageProperties().getReceivedRoutingKey());
//	}

	@RabbitListener(queues = "#{autoDeleteQueue2.name}")
	public void receive2(Message message, String in) throws InterruptedException {
		receive(in, 2, message.getMessageProperties().getReceivedRoutingKey());
	}

	public void receive(String in, int receiver,
			String receivedRoutingKey) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		log.info("instance " + receiver + " [x] Received '" + in + "' with key " + receivedRoutingKey);
		doWork(in);
		watch.stop();
		log.info("instance {} [x] Done in {} s", receiver, watch.getTotalTimeSeconds());
	}

	private void doWork(String in) throws InterruptedException {
		for (char ch : in.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
	}
}
