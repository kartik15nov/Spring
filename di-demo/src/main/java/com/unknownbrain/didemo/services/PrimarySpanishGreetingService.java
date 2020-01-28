package com.unknownbrain.didemo.services;

public class PrimarySpanishGreetingService implements GreetingService {

    private GreetingRepository repository;

    public PrimarySpanishGreetingService(GreetingRepository repository) {
        this.repository = repository;
    }

    @Override
    public String sayGreeting() {
        return repository.getSpanishGreeting();
    }
}
