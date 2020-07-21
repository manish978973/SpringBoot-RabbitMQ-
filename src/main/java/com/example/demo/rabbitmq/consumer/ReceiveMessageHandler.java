package com.example.demo.rabbitmq.consumer;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReceiveMessageHandler {
	
	public void handleMessage(String messagebody) {
	    System.out.println("THE RECEIVED PAGE IS " + messagebody);
	}

}
