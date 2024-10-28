package com.jet.breakpoints;



//import java.util.*;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class MyLambda {

    //4 BPs - Java Field Watchpoint, line, lambda, All -- fixed in 182
    private static final Predicate<Object> isNull = x -> x == null;
    //2 BPs
    private static final String str = isNull.getClass().getName();

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    private static String var;
    List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("smth");
        method1();
        method1();
        method2();
        method3(3);
        System.out.println("rwerwer");

        final ArrayList<Integer> numList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            numList.add(i);
        }

       Set<String> dffsd = new HashSet<>();

        final List<Integer> outputList = numList.stream()
                .map(x -> 2*x)
                .filter(x -> x < 50)
                .collect(Collectors.toList());
        List<Integer> values = new ArrayList<>();
        values.add(145);
        values.add(13);
        values.add(2);
        values.add(66);
        values.add(8);
        values.add(5);
        values.add(21);
        values.add(14);
        values.add(121);

        int maxBound = 10;
        values.sort((o1, o2) -> -Integer.compare(o1, o2));
        System.out.println(values);

        int minBound = 10;
        values.removeIf((val) -> val < minBound);
        System.out.println(values);

        System.out.println("lambda foreach");
        values.forEach(val -> System.out.println("val: " + val));

        List<String> hexOctValues = values.stream()
                .map(val -> Integer.toHexString(val) + " " + Integer.toOctalString(val))
                .collect(Collectors.toList());
        System.out.println("Hex/Oct values");
        hexOctValues.forEach(val -> System.out.println("hex/oct:" + val));


        int num = 15;
        final String msg = num > 10
                ? "Number is greater than 10"
                : "Number is less than or equal to 10";


        new MyLambda().acceptsFunction(opti -> {
            System.out.println(opti);
        });

        method1();
        method2();
        method3(4);

      if (isNull.test(null)) {
            int m = testMethod(3);
            System.out.println("Hello World!");

        }



        User user1 = new User(); // should stop at User class - default constructor

        user1.setName("John"); //BPs in User class
        user1.setAge(6);

        //Exception BP - NPE

        User user2 = null;
        //user2.getAge(); //--NPE is thrown

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        String s;

        List<Integer> numbersList = new ArrayList<>();
        List<Integer> numbersList2 = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            var = "" + i;
            numbersList.add(i);
            numbersList.add(i + 10);
        }

        for (int number : numbers) {
            System.out.println(number);

        }


        numbers.forEach(value -> System.out.println(value));
        numbers.forEach(System.out::println);

        System.out.println(MyLambda.sumAll(numbers, n -> n % 2 == 0));  //condition BP - removal confirmation - in later builds (182)
        System.out.println(MyLambda.sumAll(numbers, n -> n < 4));       //lambda BP


        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (a, b) -> b.compareTo(a));
        names.forEach(System.out::println);

        int v = testMethod(null);
        int k = testMethod(6);
        voidMethod(8);
        voidMethod(6);

    }

    public static int testMethod(@NotNull Integer value) {
        System.out.println("do smth");
        return value + 5;
    }

    public static void voidMethod(@NotNull Integer value) {
        MyLambda.var = Integer.toString(value);
        MyLambda.var = Integer.toString(value+3);
    }

    //ಗೂಟನ್‌ಬರ್ಗ್‌—ಜಗತ್ತನ್ನು ಸಂಪದರಿತಗೊಳಿಸಿದ ವಿಧ!
    public static int sumAll( List<Integer> numbers, Predicate<Integer> p) {  //Method BP
        int total = 0;
        for (int number : numbers) {
            if (p.test(number)) {
                total += number;
            }
        }
        return total;
    }

    public static void method1() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //invalid BP on prev empty line
    }

    public static void method2() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("recompile -> reload");

    }

    public static void method3(int s) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("recompile -> reload");

    }

    void acceptsFunction(Consumer<String> foo){

    }
}
