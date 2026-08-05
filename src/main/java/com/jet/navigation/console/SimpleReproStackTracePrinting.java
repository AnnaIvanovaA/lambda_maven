package com.jet.navigation.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.logging.Level;

public class SimpleReproStackTracePrinting {
    private static final Logger SLF4J_LOGGER = LoggerFactory.getLogger(SimpleReproStackTracePrinting.class);
    private static final java.util.logging.Logger JUL_LOGGER =
            java.util.logging.Logger.getLogger(SimpleReproStackTracePrinting.class.getName());

    public static void main(String[] args) {
        System.out.println("string".toLowerCase(Locale.ROOT));
        printEverything();
    }

    private static void printEverything() {

        RuntimeException exception = createException();


        printHeader("Throwable.printStackTrace()");


        System.out.println();
        exception.printStackTrace();


        //printHeader("Throwable.printStackTrace(System.out)");
        System.out.println();
        exception.printStackTrace(System.out);

        printHeader("Throwable stack trace printed manually");
        System.out.println(exception);

        for (StackTraceElement frame : exception.getStackTrace()) {
            System.out.println("\tat " + frame);
        }

        printHeader("Throwable stack trace converted to String");
        StringWriter buffer = new StringWriter();
        exception.printStackTrace(new PrintWriter(buffer));
        System.out.println(buffer);

        printHeader("Thread.currentThread().getStackTrace()");
        System.out.println();
        for (StackTraceElement frame : Thread.currentThread().getStackTrace()) {
            System.out.println("\tat " + frame);
        }

        printHeader("StackWalker");
        StackWalker.getInstance()
                .walk(frames -> frames.limit(20).toList())
                .forEach(frame -> System.out.println("\tat " + frame));

        printHeader("java.util.logging");
        JUL_LOGGER.log(Level.SEVERE, "JUL can print a stack trace", exception);

        printHeader("SLF4J");
        SLF4J_LOGGER.error("SLF4J can print a stack trace", exception);
    }

    private static RuntimeException createException() {
        try {
            firstLevel();
            throw new IllegalStateException("This line is unreachable");
        } catch (RuntimeException e) {
            RuntimeException wrapper = new RuntimeException("Wrapped exception for console navigation", e);
            wrapper.addSuppressed(new IllegalArgumentException("Suppressed exception example"));
            return wrapper;
        }
    }

    private static void firstLevel() {
        secondLevel();
    }

    private static void secondLevel() {
        thirdLevel();
    }

    private static void thirdLevel() {
        throw new IllegalStateException("Original failure");
    }

    private static void printHeader(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
