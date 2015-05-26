package com.capraro.rib.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rcapraro on 23/12/14.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.capraro.rib")
public class Application {


    @Autowired
    AmqpAdmin rabbitAdmin;

    public static void main(String[] args) throws Exception {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        final Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        rabbitTemplate.setMessageConverter(jsonConverter);
        Queue ribCreatedQueue = new Queue("rib.created.queue");
        rabbitAdmin.declareQueue(ribCreatedQueue);
        return rabbitTemplate;
    }

}
