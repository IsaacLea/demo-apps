package example.java.eventdriven.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	@KafkaListener(topics = "example_topic", groupId = "example_group")
	public void receiveMessage(String message) {
		System.out.println("Received message: " + message);
	}
}
