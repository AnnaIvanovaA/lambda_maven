//package com.jet.rendererTypeCheck;
//
//import com.google.common.base.Equivalence;
//import javaslang.control.Try;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//
//public class JavaslangCheck {
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        final String operation = method.getName();
//
//        final Instant t0 = time.get();
//        final Try<?> result = Try.of(() -> {
//            try {
//                return method.invoke(delegate, args);
//            } catch (InvocationTargetException e) {
//                throw e.getTargetException();
//            }
//        });
//
//        final long durationMillis = t0.until(time.get(), ChronoUnit.MILLIS);
//
//        checkResult(operation, durationMillis, result, stats);
//
//        // Propagate failure
//        if (result.isFailure()) {
//            throw result.getCause();
//        }
//
//        // Return successful result
//        return result.get();
//    }
//}
