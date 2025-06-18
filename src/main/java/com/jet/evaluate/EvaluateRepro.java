package com.jet.evaluate;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EvaluateRepro {
    public static void main(String[] args) {
        ThreadPoolExecutor x = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS, new SynchronousQueue<>() {
            @Override
            public boolean offer(Runnable runnable) {
                return super.offer(runnable); // Breakpoint here Evaluate --> super.offer((Runnable)() -> {});
            }
        });
        x.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        x.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        x.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
