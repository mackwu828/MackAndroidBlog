package com.mackwu.component.core.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2021/7/13 16:41
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyObservable{

    private final List<MyObserver> observers = new ArrayList<>();

    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MyObserver observer) {
        observers.remove(observer);
    }

    public void notifyChanged() {
        for (MyObserver observer : observers) {
            observer.update();
        }
    }

}
