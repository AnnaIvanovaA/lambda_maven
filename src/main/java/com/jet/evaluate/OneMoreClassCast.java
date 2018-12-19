package com.jet.evaluate;

import java.util.ArrayList;

public class OneMoreClassCast {

    public static void main(String[] args) {
        new OneMoreClassCast().equals(new Object());



//        System.out.println("Classloader of this class:"
//                + PrintClassLoader.class.getClassLoader());
//
//        System.out.println("Classloader of Logging:"
//                + Logging.class.getClassLoader());

        System.out.println("Classloader of ArrayList:"
                + ArrayList.class.getClassLoader());
    }

        public boolean equals(Object obj){
        OneMoreClassCast t =(OneMoreClassCast)obj;
            /**
             * If deep comparison returns true, then this equals method will return
             * true, else it will return false.
             */
            return true;
        }

}
