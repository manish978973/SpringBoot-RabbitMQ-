package com.example.demo.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rabbitmq.ConfigureRabbitMq;

@RestController
public class SendMessageController {
	
	
	private final RabbitTemplate rabbitTemplate;
	
	
	public SendMessageController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	
	@PostMapping("/send")
	public String sendMessage(@RequestParam String message) {
		rabbitTemplate.convertAndSend(ConfigureRabbitMq.EXCHANGE_NAME,"mrc.messages",message);
		return "Message Sent successfully to Rabbit MQ" + message;
	}

}
