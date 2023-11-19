package org.example.proxy;

import org.example.annotation.Proxy;
import org.example.handler.LoggingHandler;
import org.example.service.ExampleService;

import java.lang.reflect.Method;

@Proxy
public class ExampleServiceProxy implements ExampleService {

    private final ExampleService implementation;
    private final LoggingHandler handler;

    public ExampleServiceProxy(ExampleService implementation) {
        this.implementation = implementation;

        handler = new LoggingHandler(implementation);
    }

    @Override
    public void exampleMethod() {
        final String methodName = "exampleMethod";

        invokeProxyMethod(methodName, new Object[0]);
    }

    @Override
    public void anotherExampleMethod(int first, int second) {
        final String methodName = "anotherExampleMethod";

        final Object[] args = new Object[]{first, second};
        final Class<?>[] types = new Class[]{int.class, int.class};

        invokeProxyMethod(methodName, args, types);
    }

    @Override
    public void lastExampleMethod() {
        final String methodName = "lastExampleMethod";

        invokeProxyMethod(methodName, new Object[0]);
    }

    private void invokeProxyMethod(String methodName, Object[] args, Class<?>... types) {
        try {
            final Method method = implementation.getClass().getDeclaredMethod(methodName, types);

            handler.invoke(implementation, method, args);
        } catch (NoSuchMethodException e) {
            System.err.println("No such method found in the original class");
            throw new RuntimeException(e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
