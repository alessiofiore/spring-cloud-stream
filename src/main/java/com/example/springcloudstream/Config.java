package com.example.springcloudstream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class Config {

    @Bean
    public Supplier<Flux<Message<?>>> supplier() {
        return () -> fluxSinks().asFlux();
    }

    @Bean
    public Consumer<Message<String>> consumer() {
        return payload -> {
            System.out.println(payload.getPayload() + " - " + payload.toString());
        };
    }

    @Bean
    public Sinks.Many<Message<?>> fluxSinks() {
        Sinks.Many<Message<?>> sink = Sinks.many().multicast().directBestEffort();
        return sink;
    }
}
