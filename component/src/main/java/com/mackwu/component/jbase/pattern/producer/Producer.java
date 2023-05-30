package com.mackwu.component.jbase.pattern.producer;

/**
 * @author MackWu
 * @since 2023/5/15 11:12
 */
public class Producer {

    BreadContainer container;

    public Producer(BreadContainer container) {
        this.container = container;
    }

    public void makeBread() {
        container.put(new Bread());
    }

}
