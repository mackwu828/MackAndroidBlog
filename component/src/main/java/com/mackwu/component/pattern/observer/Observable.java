package com.mackwu.component.pattern.observer;

/**
 * ===================================================
 * Created by MackWu on 2021/7/13 16:41
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Observable extends java.util.Observable {

    public void notifyChanged() {
        setChanged();
        notifyObservers();
    }
}
