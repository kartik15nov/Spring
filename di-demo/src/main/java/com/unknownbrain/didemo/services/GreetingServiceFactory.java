package com.unknownbrain.didemo.services;

public class GreetingServiceFactory {
    private GreetingRepository repository;

    public GreetingServiceFactory(GreetingRepository repository) {
        this.repository = repository;
    }

    public GreetingService createGreetingService(String lang) {

        switch (lang) {
            case "de":
                return new PrimaryGermanGreetingService(repository);
            case "es":
                return new PrimarySpanishGreetingService(repository);
            default:
                return new PrimaryGreetingService(repository);
        }
    }
}
