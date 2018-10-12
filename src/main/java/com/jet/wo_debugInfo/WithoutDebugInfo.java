package com.jet.wo_debugInfo;

import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;
import java.util.*;

public class WithoutDebugInfo {
    private static long longVar;
    private static int q = 50;
    private static int qq = 24;
    public static ArrayList<String> arrayList;

    public static void main(String[] args) {
        setVar();

        boolean b = WithoutDebugInfo.longVar > 0;

        //turn off the Generate debugging info option

        try {
            Integer.parseInt("xxx");
        } catch (Exception e) {
            //e should be mapped
            System.out.println(e); //1st BP here
        }

        System.out.println("!!!" + longVar);

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr.add(i);
        }

        //i should be mapped
        for (int i: arr) {
            arr.size();
            System.out.println(i); //2nd BP Here
        }


        //SeedGenerator
        final MessageDigest var1;
        try {
            var1 = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException var3) {
            throw new InternalError("internal error: SHA-1 not available.", var3);
        }

        byte var2 = (byte) ((int) System.currentTimeMillis());
        var1.update(var2);
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                try {
                    Properties var2 = System.getProperties(); //3rd BP here
                    Enumeration var3 = var2.propertyNames();

                    while (var3.hasMoreElements()) {
                        String var1x = (String) var3.nextElement();
                        var1.update(var1x.getBytes());
                        var1.update(var2.getProperty(var1x).getBytes());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }


    public static void setVar() {
        longVar = Integer.MAX_VALUE;

    }
}
