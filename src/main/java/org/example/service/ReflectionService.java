package org.example.service;

import org.example.exception.InterfaceNotFoundException;

import java.util.List;

public interface ReflectionService {

    List<Class<?>> findAnnotatedClasses(Class<?> annotation);

    Class<?> findInterface(Class<?> someClass);

    Class<?> findProxy(Class<?> interfaceClass);
}
