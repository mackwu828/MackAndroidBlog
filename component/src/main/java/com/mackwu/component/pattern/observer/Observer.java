package com.mackwu.component.pattern.observer;

import java.util.Observable;

/**
 * ===================================================
 * Created by MackWu on 2021/7/13 16:41
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Observer implements java.util.Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Observer update...");
    }
}
