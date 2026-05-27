package com.jet.navigation.console;

import com.jet.evaluate.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LogHighlight {
    public static void main(String[] args) {
        System.out.println("Hi");
        System.out.println("\u001B[2m2026-04-22T14:34:40.622+02:00\u001B[0;39m \u001B[32m INFO\u001B[0;39m \u001B[35m51572\u001B[0;39m \u001B[2m--- ");
        System.out.println("Bye");

        //log style
        System.out.println("[ERROR] [Main] Main.java:15 Something failed");
        System.out.println("2026-05-22 10:15:32 ERROR Main.java:15");

        System.err.println("failure in serr");

        //logger
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Failure happened");

        //with exception
        logger.error("Failure", new RuntimeException("Boom"));

        //long multiline
        System.out.println("""
Starting application...
Loading modules...
Error at Main.java:15
Finished
""");

        //
        System.out.printf("something from printf");
        System.out.println();
        System.out.println("LogHighlight.main");
        System.out.print("1st args = " + Arrays.toString(args));
        //System.out.print("2nd args = " + args);

        System.out.println();
        // print var from another line
        String ref = "Main.java:10";
        System.out.println(ref);

        System.out.printf("Main.java:%d%n", 10);


        System.out.println();
    }
}
