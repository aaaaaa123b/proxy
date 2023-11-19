package org.example.handler;

import org.example.annotation.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingHandler implements InvocationHandler {

    private final Object target;
    private final Logger logger;

    public LoggingHandler(Object target) {
        this.target = target;
        this.logger = LoggerFactory.getLogger(target.getClass());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Log.class)) {
            return method.invoke(target, args);
        }

        logStart(method);
        Object result = method.invoke(target, args);
        logEnd(method);

        return result;
    }

    private void logStart(Method method) {
        logger.info("Method started: {}.{}", target.getClass().getSimpleName(), method.getName());
    }

    private void logEnd(Method method) {
        logger.info("Method ended: {}.{}", target.getClass().getSimpleName(), method.getName());
    }
}
