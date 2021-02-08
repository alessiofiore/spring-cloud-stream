package com.example.springcloudstream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Sinks;

@SpringBootApplication
@RestController
public class SpringCloudStreamApplication {

    @Autowired
    private Sinks.Many<Message<?>> fluxSinks;

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamApplication.class, args);
    }

    @SuppressWarnings("unchecked")
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handleRequest(@RequestParam("msg") String msg) throws Exception {
        Message<String> message = MessageBuilder.withPayload(msg).build();
        fluxSinks.emitNext(message, null);
    }

}
