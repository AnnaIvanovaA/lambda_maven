package com.jet;

import java.security.DomainCombiner;
import java.util.List;

public class Test01foreach {

    public static void main(String[] args) throws ClassNotFoundException {

        List<Person> pl = Person.createShortList();

        System.out.println("\n=== Western Phone List ===");
        pl.forEach( p -> p.printWesternName() );

        System.out.println("\n=== Eastern Phone List ===");
        pl.forEach(Person::printEasternName);


        System.out.println("\n=== Custom Phone List ===");
        pl.forEach(p -> { System.out.println(p.printCustom(r -> "Name: " + r.getGivenName() + " EMail: " + r.getEmail())); });

    }


    private Runnable shutDownHook(DomainCombiner domain){
        return() -> Runtime.getRuntime().addShutdownHook(new Thread(() -> domain.getClass()));
    }

}
