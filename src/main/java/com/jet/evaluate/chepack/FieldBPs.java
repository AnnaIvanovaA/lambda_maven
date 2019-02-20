package com.jet.evaluate.chepack;

public class FieldBPs {
    public static void main(String[] argv) {
        FieldBPs context = new FieldBPs();
        System.out.println(context.publicAsPossible);
        System.out.println(context.packageAsPossible);
        System.out.println(context.protectedAsPossible);
        System.out.println(context.privateAsPossible);
        ((Runnable) () -> System.out.println(13)).run();
    }

    public int publicAsPossible = 1; // Breakpoint 1.
    int packageAsPossible = 2; // Breakpoint 2.
    protected int protectedAsPossible = 3; // Breakpoint 3.
    private int privateAsPossible = 4; // Breakpoint 4.
}
