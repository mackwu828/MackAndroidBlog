package com.mackwu.component.jbase.pattern.publish;

/**
 * @author MackWu
 * @since 2023/5/16 16:48
 */
public class Test {

    public static void main(String[] args) {
        Source source = Emitter::update;
        Publisher publisher = new Publisher(source);
        publisher.subscribe(() -> {

        });
    }
}
