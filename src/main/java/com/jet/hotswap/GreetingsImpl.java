package com.jet.hotswap;

public class GreetingsImpl implements Greetings {

    @Override
    public String getGreeting(String name) {
        return "Hey there, " + name + "!";  // <- modify this during hotswap
    }
}
