package com.example.springboot.rabbitmqexample.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessageConfig {
	 public static final String QUEUE = "message_queue";
	 public static final String EXCHANGE = "message_exchange";
	    public static final String ROUTING_KEY = "message_routingKey";
	
	/*@Value("${QUEUE}")
	private String messageQueue;

	@Value("${EXCHANGE}")
	private String messageExchange;
	
	@Value("${ROUTING_KEY}")
	private String messageRoutingKey;
	
	

	    public String getMessageQueue() {
		return messageQueue;
	}

	public String getMessageExchange() {
		return messageExchange;
	}

	public String getMessageRoutingKey() {
		return messageRoutingKey;
	}*/

		@Bean
	    public Queue queue() {
	        return new Queue(QUEUE);
	    }

	    @Bean
	    public TopicExchange exchange() {
	        return new TopicExchange(EXCHANGE);
	    }

	    @Bean
	    public Binding binding(Queue queue, TopicExchange exchange) {
	        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	    }

	    @Bean
	    public MessageConverter converter() {
	        return new Jackson2JsonMessageConverter();
	    }

	    @Bean
	    public AmqpTemplate template(ConnectionFactory connectionFactory) {
	        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	        rabbitTemplate.setMessageConverter(converter());
	        return rabbitTemplate;
	    }
}
