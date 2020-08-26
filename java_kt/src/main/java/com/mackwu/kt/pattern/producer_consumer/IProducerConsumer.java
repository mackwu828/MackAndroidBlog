package com.mackwu.kt.pattern.producer_consumer;

/**
 * ===================================================
 * Created by MackWu on 2020/4/23 17:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public interface IProducerConsumer {

    void produce();

    void consume();

    void stop();
}
