package com.mackwu.component.jbase.pattern.observer;

/**
 * @author MackWu
 * @since 2023/5/15 19:26
 */
public interface Subject {

    /**
     * 添加观察者
     * @param observer 观察者
     */
    void addObserver(Observer observer);

    /**
     * 移除观察者
     * @param observer 观察者
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有观察者数据发生改变
     */
    void update();

}
