package com.mackwu.component.jbase.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MackWu
 * @since 2023/5/16 10:31
 */
public class SubjectImpl implements Subject{

    List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void update() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
