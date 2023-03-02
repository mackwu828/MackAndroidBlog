package com.mackwu.component.jbase;

/**
 * @author MackWu
 * @since 2022/10/25 15:46
 * 并发
 */
public class VolatileTest {

    public volatile static boolean ready;

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread is running...");
            while (!ready){
                System.out.println("MyThread is running...");
            }
            System.out.println("MyThread is end");
        }
    }

    /**
     * MyThread is running...
     * ready=true
     * main thread is end
     *
     * MyThread is running...
     * MyThread is end
     * ready=true
     * main thread is end
     */
    public static void main(String[] args) throws InterruptedException {
        new MyThread().start();
        Thread.sleep(1000);
        ready = true;
        System.out.println("ready=" + ready);
        Thread.sleep(5000);
        System.out.println("main thread is end");
    }

}
