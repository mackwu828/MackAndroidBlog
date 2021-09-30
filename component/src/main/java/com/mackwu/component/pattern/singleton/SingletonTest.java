package com.mackwu.component.pattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ===================================================
 * Created by MackWu on 2021/9/15 14:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class SingletonTest {

    static class A {
//        private static A instance;
//
//        private A() {
//        }
//
//        public static A getInstance() {
//            if (instance == null) {
//                instance = new A();
//            }
//            return instance;
//        }

        private static A instance;

        private A() {
        }

        public static A getInstance() {
            if (instance == null) {
                synchronized (A.class) {
                    if (instance == null) {
                        instance = new A();
                    }
                }
            }
            return instance;
        }
    }

    static class B {
        private static B instance = new B();

        private B() {
        }

        public static B getInstance() {
            return instance;
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> System.out.println(Thread.currentThread().getName() + ": " + A.getInstance()));
        }
        executorService.shutdown();
    }

}
