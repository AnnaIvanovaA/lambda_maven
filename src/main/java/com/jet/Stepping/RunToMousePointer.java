package com.jet.Stepping;

import com.jet.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunToMousePointer {

    public static void main(String[] args) {
        method1();

        createUserList();
    }

    private static void createUserList() {
        List<Person> pl = Person.createShortList();
    }


    public static void method1() {
        String key = "key";
        String anotherKey="another";
        if (key.equals(anotherKey)){
            key ="change";
        }
        Map<String, Integer> mapa = new HashMap<>();
        mapa.put(key, 3);
    }


}
