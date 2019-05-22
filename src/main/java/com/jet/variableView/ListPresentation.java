package com.jet.variableView;

import java.time.LocalDateTime;
import java.util.*;

public class ListPresentation {
    public static void main(String[] args) {

        boolean result = true;
        char capitalC = 'C';
        char c = '\uffff';
        char c2 = '\u0108';
        char c3 = '\u0000';
        char c4 = '\u039A';

        byte b = 100;
        short s = 10000;
        int i = 100000;
        float f = 3.0f;
        double d2 = 1.234e2;

        // The number 26, in decimal
        int decVal = 26;
//  The number 26, in hexadecimal
        int hexVal = 0x1a;
// The number 26, in binary
        int binVal = 0b11010;




        //underscore in numerics
        long creditCardNumber = 1234_5678_9012_3456L;
        long socialSecurityNumber = 999_99_9999L;
        float pi =  3.14_15F;
        long hexBytes = 0xFF_EC_DE_5E;
        long hexWords = 0xCAFE_BABE;
        long maxLong = 0x7fff_ffff_ffff_ffffL;
        byte nybbles = 0b0010_0101;
        long bytes = 0b11010010_01101001_10010100_10010010;

        List<Object> listPrimitiveTypes = new ArrayList<>();
        listPrimitiveTypes.add("str");
        listPrimitiveTypes.add( 1);
        listPrimitiveTypes.add(2.0d);
        listPrimitiveTypes.add(3L);
        listPrimitiveTypes.add(System.currentTimeMillis());
        listPrimitiveTypes.add(new Date());
        listPrimitiveTypes.add(LocalDateTime.now());
        listPrimitiveTypes.add('c');

        listPrimitiveTypes.add(result);
        listPrimitiveTypes.add(capitalC);
        listPrimitiveTypes.add(c);
        listPrimitiveTypes.add(c2);
        listPrimitiveTypes.add(c3);
        listPrimitiveTypes.add(c4);

        listPrimitiveTypes.add(b);
        listPrimitiveTypes.add(s);
        listPrimitiveTypes.add(i);
        listPrimitiveTypes.add(f);
        listPrimitiveTypes.add(d2);
        listPrimitiveTypes.add(decVal);
        listPrimitiveTypes.add(hexVal);
        listPrimitiveTypes.add(binVal);
        listPrimitiveTypes.add(creditCardNumber);
        listPrimitiveTypes.add(socialSecurityNumber);
        listPrimitiveTypes.add(pi);
        listPrimitiveTypes.add(hexWords);
        listPrimitiveTypes.add(hexBytes);
        listPrimitiveTypes.add(maxLong);
        listPrimitiveTypes.add(nybbles);
        listPrimitiveTypes.add(bytes);

        System.out.println(listPrimitiveTypes.size());

    }
}
