package com.example.springboot.rabbitmqexample.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.rabbitmqexample.Bean.Order;
import com.example.springboot.rabbitmqexample.Bean.OrderStatus;
import com.example.springboot.rabbitmqexample.config.MessageConfig;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	
	@PostMapping("/{restaurentName}")
	public String bookOrder(@RequestBody Order order,@PathVariable String restaurentName) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderStatus orderStatus = new OrderStatus(order,"PROCESS","order placed successfully in  "+restaurentName);
		rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE,MessageConfig.ROUTING_KEY,orderStatus);
		return "SUCESSS!!";
	}
}
