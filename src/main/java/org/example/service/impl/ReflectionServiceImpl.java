package org.example.service.impl;

import org.example.annotation.Component;
import org.example.annotation.Proxy;
import org.example.exception.InterfaceNotFoundException;
import org.example.exception.ProxyNotFoundException;
import org.example.service.ReflectionService;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ReflectionServiceImpl implements ReflectionService {

    private final Reflections reflections;

    public ReflectionServiceImpl(String packageName) {
        this.reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(packageName)
                .addScanners(new SubTypesScanner(), new TypeAnnotationsScanner()));
    }

    public List<Class<?>> findAnnotatedClasses(Class<?> annotation) {
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith((Class<? extends Annotation>) annotation);

        return new ArrayList<>(annotatedClasses);
    }

    public Class<?> findInterface(Class<?> someClass){
        final Class<?>[] interfaces = someClass.getInterfaces();

        if (interfaces.length != 1) {
            throw new InterfaceNotFoundException("Class " + someClass.getName() + " should implement interface");
        }

        return interfaces[0];
    }

    public Class<?> findProxy(Class<?> interfaceClass) {
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Proxy.class);

        for (Class<?> annotatedClass : annotatedClasses) {
            if (Arrays.asList(annotatedClass.getInterfaces()).contains(interfaceClass)) {
                return annotatedClass;
            }
        }

        throw new ProxyNotFoundException("Proxy not found for interface: " + interfaceClass.getName());
    }
}
