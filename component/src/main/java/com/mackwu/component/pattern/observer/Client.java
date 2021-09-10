package com.mackwu.component.pattern.observer;

/**
 * ===================================================
 * Created by MackWu on 2021/7/13 16:44
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Client {

    public static void main(String[] args) {
        Observable observable = new Observable();
        observable.addObserver(new Observer());
        observable.addObserver(new Observer());
        observable.notifyChanged();
    }
}
