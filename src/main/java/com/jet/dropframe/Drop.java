package com.jet.dropframe;

public class Drop {

    public static void main(String[] args) {
        var s = "foo";
        toStringLowerCase(s); // 2) change value of s variable, step into
    }

    private static String toStringLowerCase(String t) {
        return t.toLowerCase(); // 1) breakpoint; drop frame here, 3) observe the wrong value
    }

}
