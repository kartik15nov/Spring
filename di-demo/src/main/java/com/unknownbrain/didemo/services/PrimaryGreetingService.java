package com.unknownbrain.didemo.services;

public class PrimaryGreetingService implements GreetingService {

    private GreetingRepository repository;

    public PrimaryGreetingService(GreetingRepository repository) {
        this.repository = repository;
    }

    @Override
    public String sayGreeting() {
        return repository.getEnglishGreeting();
    }
}


