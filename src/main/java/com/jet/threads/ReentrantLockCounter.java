package com.jet.threads;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Counter{

    private final ReentrantLock reentrantLock = new ReentrantLock();
    private int value;
    @Override
    public void increment() {
        reentrantLock.lock();
        try{
            value++;
        } finally {
            reentrantLock.unlock();
        }

    }

    @Override
    public int getValue() {
        return value;
    }
}
