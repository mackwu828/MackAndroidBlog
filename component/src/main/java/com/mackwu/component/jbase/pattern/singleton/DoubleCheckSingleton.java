package com.mackwu.component.jbase.pattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author MackWu
 * @since 2022/6/28 16:31
 */
public class DoubleCheckSingleton {

    private static DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 50; i++) {
            executorService.execute(() -> System.out.println(Thread.currentThread().getName() + ": " + DoubleCheckSingleton.getInstance()));
        }
        executorService.shutdown();
    }
}
