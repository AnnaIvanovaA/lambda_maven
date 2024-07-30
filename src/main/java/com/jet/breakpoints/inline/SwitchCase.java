package com.jet.breakpoints.inline;

import com.jet.Stepping.StepOutBlock;
import org.w3c.dom.ls.LSOutput;

import java.util.Calendar;
import java.util.stream.Stream;

import static java.util.Calendar.*;

public class SwitchCase {


    public static void main(String[] args) {

        Stream.of(1, 2, 3).forEach(System.out::println);
        Stream.of(1, 2, 3).forEach(x -> System.out.println(x));

        System.out.println(NOVEMBER);
        System.out.println(getMonthNumber(SEPTEMBER));

    }

     public static Integer getMonthNumber(Integer month){
        switch (month) {
            case MAY -> {return 3333;}
            case DECEMBER, JANUARY, JUNE, JULY -> { return 3; }
            case NOVEMBER, SEPTEMBER,OCTOBER -> {
                return 5; }
            case MARCH -> { return 2; }
            default -> { return 0; }
        }
    }

}

