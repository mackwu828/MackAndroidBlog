package com.mackwu.component.jbase;

/**
 * ===================================================
 * Created by MackWu on 2022/5/17 18:25
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class SyncTest {


    /**
     * synchronized修饰方法。加锁的对象是这个方法所在实例本身
     * synchronized修饰静态方法，加锁的对象是静态方法所在类的Class对象。
     * synchronized修饰代码块。加锁的对象是传入的对象实例。
     */
    public void add() {
        synchronized (this) {

        }
    }


    public static void main(String[] args) {

    }

}
