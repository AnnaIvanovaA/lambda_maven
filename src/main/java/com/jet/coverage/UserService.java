package com.jet.coverage;

public class UserService {
    private final AddAndDivide calculator;

    public UserService(AddAndDivide calculator) {
        this.calculator = calculator;
    }

    public int calculateUserScore(int tasksCompleted, int errors) {
        if (tasksCompleted < 0 || errors < 0) throw new IllegalArgumentException("Invalid numbers");
        return calculator.add(tasksCompleted, -errors);
    }
}
