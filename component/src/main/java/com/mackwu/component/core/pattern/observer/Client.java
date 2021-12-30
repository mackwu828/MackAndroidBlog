package com.mackwu.component.core.pattern.observer;

/**
 * ===================================================
 * Created by MackWu on 2021/7/13 16:44
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Client {

    public static void main(String[] args) {
        MyObservable observable = new MyObservable();
        observable.addObserver(new MyObserver());
        observable.addObserver(new MyObserver());
        observable.notifyChanged();
    }
}
