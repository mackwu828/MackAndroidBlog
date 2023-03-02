package com.mackwu.component.jbase;

/**
 * @author MackWu
 * @since 2022/6/13 14:37
 */
public class WaitNotifyTest {

    public final Object lock = new Object();

    public void test1() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ", test1 wait");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", test1 continue");
        }
    }

    public void test2() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ", test2 begin");
            try {
                Thread.sleep(3000);
                lock.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", test2 end");
        }
    }

    public static void main(String[] args) {

    }

}
