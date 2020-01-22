package com.unknownbrain.didemo;

import com.unknownbrain.didemo.controllers.ConstructorInjectedController;
import com.unknownbrain.didemo.controllers.MyController;
import com.unknownbrain.didemo.controllers.PropertyInjectedController;
import com.unknownbrain.didemo.controllers.SetterInjectedController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DiDemoApplication.class, args);

        //We can get a bean by asking the application context;
        MyController myController = (MyController) context.getBean("myController");

        System.out.println(myController.hello());
        System.out.println(context.getBean(PropertyInjectedController.class).sayHello());
        System.out.println(context.getBean(SetterInjectedController.class).sayHello());
        System.out.println(context.getBean(ConstructorInjectedController.class).sayHello());
    }
}
