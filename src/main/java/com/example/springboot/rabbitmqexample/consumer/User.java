package com.example.springboot.rabbitmqexample.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.springboot.rabbitmqexample.Bean.OrderStatus;
import com.example.springboot.rabbitmqexample.config.MessageConfig;
import com.example.springboot.rabbitmqexample.config.MessageConfig;

@Component
public class User {

	
	@RabbitListener(queues = MessageConfig.QUEUE)
   public void consumeMessageFromQueue(OrderStatus orderStatus) {
       System.out.println("Message received from queue : "+ orderStatus);
   }
}
