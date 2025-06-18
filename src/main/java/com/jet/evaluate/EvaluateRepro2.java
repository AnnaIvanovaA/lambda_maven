package com.jet.evaluate;
import java.util.concurrent.SynchronousQueue;

public class EvaluateRepro2 {
    public static void main(String[] args) {
        SynchronousQueue<Object> q = new SynchronousQueue<>(){
            @Override
            public boolean offer(Object o) {
                return super.offer((Runnable)() -> {}); // Debugger here Evaluate super.offer(() -> {})
            }
        };
        new Thread(q::poll).start();
        q.offer((Runnable)() -> {});

    }
}
