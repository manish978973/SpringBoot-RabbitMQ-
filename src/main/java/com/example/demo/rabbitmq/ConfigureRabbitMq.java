package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.rabbitmq.consumer.ReceiveMessageHandler;

@Configuration
public class ConfigureRabbitMq {
	
	public static final String EXCHANGE_NAME = "mrcexchange";
	public static final String QUEUE_NAME = "mrcqueue";
	
	
	@Bean
	Queue createQueue() {
		return new Queue(QUEUE_NAME, false);	
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}
	
	@Bean
	Binding binding (Queue queue,TopicExchange topicexchange) {
		return BindingBuilder.bind(queue).to(topicexchange).with("mrc.#");
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionfactory, MessageListenerAdapter messageListenerAdapter ) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionfactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(messageListenerAdapter);
		return container;	
	}
	
	
	@Bean
	MessageListenerAdapter listenerAdapter(ReceiveMessageHandler handler) {
		return new MessageListenerAdapter(handler, "handleMessage");
	}
	
	
	

}
