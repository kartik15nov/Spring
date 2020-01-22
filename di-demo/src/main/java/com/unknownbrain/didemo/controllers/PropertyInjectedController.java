package com.unknownbrain.didemo.controllers;

import com.unknownbrain.didemo.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PropertyInjectedController {

    @Autowired
    @Qualifier("greetingServiceImpl")
    public GreetingService greetingServiceImpl; //If @Qualifier annotation is commented out then Even though the reference name is same as GreetingServiceImpl class name,
    //Because of the @Primary annotation in PrimaryGreetingService.class, spring loads the primary bean if the qualifier is not defined.

    public String sayHello() {
        return greetingServiceImpl.sayGreeting();
    }
}
