package com.capraro.rib.controller;

import com.capraro.model.Rib;
import org.apache.catalina.User;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rcapraro on 23/12/14.
 */
@RestController
public class RibController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/ribs", method = RequestMethod.GET)
    public String createRib() {
        Rib u = (Rib)
                rabbitTemplate.convertSendAndReceive("rib.created.queue", new Rib(new Long(1), "gjh"),
                        message -> {
                            message.getMessageProperties().setHeader("name", "revo");
                            System.out.println("recive");
                            return message;
                        }
                );

        System.out.println(u.getId());
        return "Rib created: ";
    }
}
