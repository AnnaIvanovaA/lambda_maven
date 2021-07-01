package com.jet.variableView;

public class VarViewRepro {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            final String checkString = "smth"+i;
            if (checkString.equals("smth0")){
                i++;
            }
            else if (checkString.equals("smth1")){
                i++;
            }
            else{
                i=i*2;
            }
        }


    }
}
