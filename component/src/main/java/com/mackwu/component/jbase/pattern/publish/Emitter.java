package com.mackwu.component.jbase.pattern.publish;

/**
 * @author MackWu
 * @since 2023/5/16 17:05
 */
public class Emitter {

    private final Observer observer;

    public Emitter(Observer observer) {
        this.observer = observer;
    }

    public void update() {
        observer.update();
    }

}
