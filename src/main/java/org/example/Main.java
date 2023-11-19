package org.example;

import org.example.context.ApplicationContext;
import org.example.service.ExampleService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext("org.example");

        ExampleService service = (ExampleService) context.find(ExampleService.class);

        service.exampleMethod();
        service.lastExampleMethod();
        service.anotherExampleMethod(1, 2);
    }
}
