package example.java.eventdriven.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import example.java.eventdriven.demo.service.KafkaProducerService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@Test
	void contextLoads() {
	}

	@Test
	void testKafkaProducerService() {
		kafkaProducerService.sendMessage("Hello, Kafka!");
	}

}
