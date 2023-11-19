package org.example.context;

import org.example.annotation.Component;
import org.example.exception.ContextInitializationException;
import org.example.exception.InterfaceNotFoundException;
import org.example.service.ReflectionService;
import org.example.service.impl.ReflectionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContext.class);

    private final Map<Class<?>, Object> dependencies = new HashMap<>();

    public ApplicationContext(String packageName) {
        prepareProxies(packageName);

        LOGGER.info("Application context was successfully initialized");
    }

    private void prepareProxies(String packageName) {
        final ReflectionService reflection = new ReflectionServiceImpl(packageName);

        try {
            List<Class<?>> serviceClasses = reflection.findAnnotatedClasses(Component.class);

            for (Class<?> serviceClass : serviceClasses) {
                LOGGER.info("Found class with @Service annotation: " + serviceClass.getName());

                final Class<?> interfaceType = reflection.findInterface(serviceClass);

                Class<?> proxyClass = reflection.findProxy(interfaceType);
                LOGGER.info("Found class with @Proxy annotation: " + proxyClass.getName());

                Object service = serviceClass.getConstructor().newInstance();
                Object proxy = proxyClass.getConstructor(interfaceType).newInstance(service);

                dependencies.put(interfaceType, proxy);
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new ContextInitializationException(e);
        } catch (InterfaceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Object find(Class<?> clazz) {
        final Object dependency = dependencies.get(clazz);

        return Optional.ofNullable(dependency)
                .orElseThrow(() -> new NoSuchElementException(clazz.getName()));
    }
}
