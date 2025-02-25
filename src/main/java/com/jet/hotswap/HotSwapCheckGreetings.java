package com.jet.hotswap;

public class HotSwapCheckGreetings {
    public static void main(String[] args) throws InterruptedException {
        Greetings service = new GreetingsImpl();

        new HotSwapCheckGreetings().start(service);
    }

    public void start(Greetings service) throws InterruptedException {
        String[] names = {"Alice", "Bob", "Charlie", "Ben"};

        while (true) {
            for (String name : names) {
                System.out.println(service.getGreeting(name));
            }
            System.out.println("---- Waiting for 11 seconds ----\n"); // <- modify this during hotswap
            Thread.sleep(11000);  // <- modify this during hotswap
        }
    }
}
