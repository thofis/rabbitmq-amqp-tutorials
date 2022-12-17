package com.example.rabbitmqamqptutorials.tut3;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@Slf4j
public class Tut3Receiver {
	@RabbitListener(queues = "#{autoDeleteQueue1.getName()}")
	public void receive1(String in) throws InterruptedException {
		receive(in, 1);
	}

	@RabbitListener(queues = "#{autoDeleteQueue2.getName()}")
	public void receive2(String in) throws InterruptedException {
		receive(in, 2);
	}

	private void receive(String in,
			int i) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		log.info("instance {} [x} received '{}'", i, in);
		doWork(in);
		watch.stop();
		log.info("instance {} [x} done in {} s", i, watch.getTotalTimeSeconds());
	}

	private void doWork(String in) throws InterruptedException {
		for (char ch : in.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
	}
}
