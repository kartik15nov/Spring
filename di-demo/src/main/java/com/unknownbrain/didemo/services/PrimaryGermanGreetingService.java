package com.unknownbrain.didemo.services;

public class PrimaryGermanGreetingService implements GreetingService {

    private GreetingRepository repository;

    public PrimaryGermanGreetingService(GreetingRepository repository) {
        this.repository = repository;
    }

    @Override
    public String sayGreeting() {
        return repository.getGermanGreeting();
    }
}
