package com.unknownbrain.didemo.services;

import org.springframework.stereotype.Component;

@Component
public class GreetingRepositoryImpl implements GreetingRepository {

    @Override
    public String getEnglishGreeting() {
        return "Hello!! - Greeting from PrimaryGreetingService";
    }

    @Override
    public String getSpanishGreeting() {
        return "¡¡Hola!! - Saludo desde PrimaryGreetingService";
    }

    @Override
    public String getGermanGreeting() {
        return "Hallo!! - Begrüßung von PrimaryGreetingService";
    }
}
