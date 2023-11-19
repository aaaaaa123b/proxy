package org.example.service.impl;

import org.example.annotation.Log;
import org.example.annotation.Component;
import org.example.service.ExampleService;

@Component
public class ExampleServiceImpl implements ExampleService {

    @Log
    @Override
    public void exampleMethod() {
        System.out.println("0");
    }

    @Log
    @Override
    public void anotherExampleMethod(int first, int second) {
        System.out.println("1");
    }

    @Override
    public void lastExampleMethod() {
        System.out.println("2");
    }
}
