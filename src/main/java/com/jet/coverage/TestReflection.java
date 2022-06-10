/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.jet.coverage;

import java.lang.reflect.Field;

public class TestReflection {
    public static class A {
        public static int x = 1;
        public int y = 2;
    }

    public static void main(String[] args) throws IllegalAccessException {
        final A instance = new A();
        final Field[] fields = A.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getInt(instance));
        }
    }
}
