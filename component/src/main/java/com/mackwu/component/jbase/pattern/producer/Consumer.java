package com.mackwu.component.jbase.pattern.producer;

/**
 * @author MackWu
 * @since 2023/5/15 11:20
 */
public class Consumer {

    BreadContainer container;

    public Consumer(BreadContainer container) {
        this.container = container;
    }

    public void takeBread() {
        for (; ; ) {
            Bread bread = container.take();
//            bread.eat();
        }
    }

    public static void main(String[] args) {
        BreadContainer container = new BreadContainer();
        Producer producer = new Producer(container);
        Consumer consumer = new Consumer(container);
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                producer.makeBread();
            }
        }).start();
        consumer.takeBread();
    }

}
