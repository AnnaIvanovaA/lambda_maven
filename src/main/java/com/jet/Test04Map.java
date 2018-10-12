package com.jet;

import java.util.List;
import java.util.OptionalDouble;

public class Test04Map {

    public static void main(String[] args) {
        List<Person> pl = Person.createShortList();

        SearchCriteria search = SearchCriteria.getInstance();

        // Calc average age of pilots old style
        System.out.println("== Calc Old Style ==");
        int sum = 0;
        int count = 0;

        for (Person p:pl){
            if (p.g() >= 23 && p.g() <= 65 ){
                sum = sum + p.g();
                count++;
            }
        }

        long average = sum / count;
        System.out.println("Total Ages: " + sum);
        System.out.println("Average Age: " + average);


        // Get sum of ages
        System.out.println("\n== Calc New Style ==");
        long totalAge = pl
                .stream()
                .filter(search.getCriteria("allPilots"))
                .mapToInt(p -> p.g())
                .sum();

        // Get average of ages
        OptionalDouble averageAge = pl
                .parallelStream()
                .filter(search.getCriteria("allPilots"))
                .mapToDouble(p -> p.g())
                .average();

        System.out.println("Total Ages: " + totalAge);
        System.out.println("Average Age: " + averageAge.getAsDouble());

    }

}