package com.jet.Stepping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class SmartStepInto {
    //for several method calls, it's possible to choose in which one to step

    public static void main(String[] args) throws IOException { //BP here for lambda
        //lambda
        bar(() -> System.out.println("Hello"));

        String s = "5";
        System.out.println( Integer.parseInt(
                (int) Float.parseFloat(
                        Double.parseDouble(
                                Integer.parseInt(s) + "") + "")+""));

        //simple
        (new SmartStepInto()).check(foo() // with a breakpoint on this line
                + boo()
                + boo()
                + foo()
                + foo()
                + foo()
                + boo()
                + foo()
                + foo("qq")
                + boo(5));

        (new SmartStepInto()).check(foo() + boo() + boo() + foo() + foo("qq") + boo(5) + foo() + boo());

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in)); //BP
        String line;
        while (!((line = rd.readLine()).equals("exit"))){
            System.out.println(line);
        }

        Stream.of(1,2,3,4,5,6,7).count(); // here
        //Stream.of(1,2,3,4,5,6,7).map(n -> doubleN(n)).filter(a -> less(a, 8)).map(x -> doubleN(x)).filter(b -> less(b, 16)).map(k -> doubleN(k)).filter(c -> less(c, 48)).forEach(System.out::println);
        Stream.of(1,2,3,4,5,6,7)
                .map(n -> doubleN(n))
                .filter(a -> less(a, 8))
                .map(x -> doubleN(x))
                .filter(b -> less(b, 16))
                .map(k -> doubleN(k))
                .filter(c -> less(c, 48))
                .forEach(System.out::println);

        //Anonymous class
        SaySmth user1 = new SaySmth() {
            @Override
            public void sayFoo() {
                System.out.println("user1 foo");
            }

            @Override
            public void sayBoo() {
                System.out.println("user1 boo");
            }

            @Override
            public void sayFoo(String str) {
                System.out.println("foo string");
            }
        };
        user1.sayBoo();
        user1.sayFoo();
        user1.sayFoo("qwerty");


        //Anonymous class with callback
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        Divider.divide(a, b, new Callback() { // passing callback as an argument

            @Override
            public void calculated(int result) {
                String textToPrint = String.format("%d / %d is %d", a, b, result);
                print(textToPrint);
            }

            @Override
            public void failed(String errorMsg) {
                print(errorMsg);
            }
        });

        //Anonymous class
        new SmartStepInto().foo1(new Function<String, Integer>() { // step into apply method here
            @Override
            public Integer apply(String integer1) {
                return 5;
            }
        });
    }
    <T,R> void foo1(Function<T,R> r) {
        r.apply((T)"dd");
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public void check(String str){
        System.out.println(str);
    }

    static int doubleN(int a) {
        return a*2;
    }

    static boolean less(int a, int b) {
        return a < b;
    }

    public static String foo(){
        return "foo";
    }

    public static String foo(String s){
        System.out.println(s);
        return s;
    }

    private static String boo(){
        return "boo";
    }

    private static String boo(int i){
        return Integer.toString(i);
    }

    private static void bar(Runnable r) {
        r.run();
    }
}
