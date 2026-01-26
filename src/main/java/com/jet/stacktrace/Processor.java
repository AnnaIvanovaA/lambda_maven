package com.jet.stacktrace;


public class Processor {
    public void processStepTwo() {
        new Helper().performFinalAction();
    }

    public void processStepOne() {
        processStepTwo();
    }
}
