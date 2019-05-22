package com.jet.Stepping.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicInvocationHandler implements InvocationHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(
            DynamicInvocationHandler.class);

    public DynamicInvocationHandler() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        LOGGER.debug("Invoked method: {}", method.getName());
        //LOGGER.info("Invoked method: {}", method.getName());

        if (method.getName().equals("get")) {
            return 42;
        } else {
            throw new UnsupportedOperationException(
                    "Unsupported method: " + method.getName());
        }
    }
}
