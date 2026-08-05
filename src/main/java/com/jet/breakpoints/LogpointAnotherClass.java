package com.jet.breakpoints;

import com.jet.Stepping.StepOutBlock;

import java.util.List;

public class LogpointAnotherClass {
    static void keepLineWhenCompletionIsBeforeStatementSemicolon(List<String> myList) {
        // QA: invoke command completion before the semicolon in the next line: myList.size()..;
        // Expected: logpoint is added, and this line stays in the editor.


        System.out.println("three");


        //myList.size();
        System.out.println("sout from another class");
    }

}