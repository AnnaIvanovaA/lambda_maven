/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.jet.terminal;

public class UtfCheck {
    public static final String USER_ICON = "\uD83D\uDC64";
    private static final String TEXT_RESET = "\u001B[0m";


    public static void main(String[] args) {

        System.out.println("Icon: " + USER_ICON + TEXT_RESET);
        System.out.println("a\u0300a\u0300");
        System.out.println("a\u0320a\u0320");
        System.out.println("a\u0332a\u0332");
        System.out.println("a\u0335a\u0335");
        System.out.println("a\u0336a\u0336");
        System.out.println("a\u0338a\u0338");
        System.out.println("a\u033Ca\u033C");
        System.out.println("a\u0347a\u0347");
        System.out.println("a\u035Fa\u035F");

        System.out.println();


    }
}
