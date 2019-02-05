package com.jet.evaluate.chepack;

public class SwitchCase {

    static int month = 1;

    public static void main(String[] args) {
        System.out.println("smth");
        switchMethod1();
        switchMethod2();
        changeMonth();
        returnMonth();
    }

    public static void switchMethod1(){
        int month = 8;
        String monthString;
        switch (month) {
            case 1:  monthString = "January";
                break;
            case 2:  monthString = "February";
                break;
            case 3:  monthString = "March";
                break;
            case 4:  monthString = "April";
                break;
            case 5:  monthString = "May";
                break;
            case 6:  monthString = "June";
                break;
            case 7:  monthString = "July";
                break;
            case 8:  monthString = "August";
                break;
            case 9:  monthString = "September";
                break;
            case 10: monthString = "October";
                break;
            case 11: monthString = "November";
                break;
            case 12: monthString = "December";
                break;
            default: monthString = "Invalid month";
                break;
        }
        System.out.println(monthString);
    }

    public static String switchMethod2(){
        String tag = "groovy";
        switch(tag) {
            case "java":
                return "How to generate xml report with maven depencency?";
            case "scala":
                return "Update a timestamp SettingKey in an sbt 0.12 task";
            case "groovy":
                return "Reusing Grails variables inside Config.groovy";
            case "clojure":
                return "Merge two comma delimited strings in Clojure";
            default:
                return "smth";
        }
    }

    public static void changeMonth(){
        Month monthName = new Month();
        switch (month) {
            case 1:  monthName.setMonthString("January");
                break;
            case 2:  monthName.setMonthString("February");
                break;
            case 3:  monthName.setMonthString("March");
                break;
            case 4:  monthName.setMonthString("April");
                break;
            case 5:  monthName.setMonthString("May");

            default: monthName.setMonthString("Invalid month");
                break;
        }
       monthName.getMonthString();
    }

    public static String returnMonth(){
        switch (month) {
            case 1:
               return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
        }
        return "smth";
    }
}
