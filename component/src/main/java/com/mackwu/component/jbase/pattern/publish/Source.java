package com.mackwu.component.jbase.pattern.publish;

/**
 * @author MackWu
 * @since 2023/5/16 17:10
 */
public interface Source {

    void onSubscribe(Emitter emitter);
}
