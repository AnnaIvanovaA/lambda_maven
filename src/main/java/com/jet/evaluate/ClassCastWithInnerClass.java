package com.jet.evaluate;


public class ClassCastWithInnerClass {
    public static void main(String[] args) throws Exception {
        class LocalClass{}
        Object l = new LocalClass();
        LocalClass l1 = (LocalClass)l;


        //ClassCast exc
        Object olocal = new LocalClass();
        Integer ilocal = (Integer) olocal;

        //returns result



        //ClassCastExc
        //Evaluate: Cannot cast String to Integer
        Object o = "Hello";
        //Integer i = (Integer) o;


        //ClassCastExc - cannot cast Integer to String
        Object iObj = Integer.valueOf(42);
        String s = (String) iObj;





    }
}



