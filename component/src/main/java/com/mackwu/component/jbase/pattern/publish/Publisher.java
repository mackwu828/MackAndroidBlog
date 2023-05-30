package com.mackwu.component.jbase.pattern.publish;

/**
 * @author MackWu
 * @since 2023/5/16 16:14
 */
public class Publisher {

    private final Source source;

    public Publisher(Source source) {
        this.source = source;
    }

    public void subscribe(Observer observer) {
        Emitter emitter = new Emitter(observer);
        source.onSubscribe(emitter);
    }

}
