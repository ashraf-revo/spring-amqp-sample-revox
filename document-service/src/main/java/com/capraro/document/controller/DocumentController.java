package com.capraro.document.controller;

import com.capraro.model.Rib;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rcapraro on 23/12/14.
 */
@RestController
public class DocumentController {


@Autowired
RabbitTemplate mq;
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RabbitListener(queues = "rib.created.queue")
    public void processRib(Rib rib,Message message) {
        System.out.println("wea"+rib);
        rib.setId(new Long(2));
        System.out.println(message.getMessageProperties().getHeaders().get("name").toString());
        mq.convertAndSend(message.getMessageProperties().getReplyTo(), rib);
    }
}
