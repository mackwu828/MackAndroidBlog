package com.mackwu.component.jbase.pattern.producer;


import java.util.LinkedList;

/**
 * @author MackWu
 * @since 2023/5/15 11:13
 */
public class BreadContainer {

    LinkedList<Bread> list = new LinkedList<>();
    private final static int CAPACITY = 10;

    /**
     * 放入面包
     */
    public synchronized void put(Bread bread) {
        while (list.size() == CAPACITY) {
            // 如果容器已满，则则塞生产者线程
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        list.add(bread);
        // 面包生成成功后通知消费者线程
        notify();
        System.out.println(Thread.currentThread().getName() + " product a bread, size=" + list.size());
    }

    /**
     * 取出面包
     */
    public synchronized Bread take() {
        while (list.isEmpty()) {
            // 如果容器为空，则阻塞消费者线程
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Bread bread = list.removeFirst();
        // 消费后通知生产者生产面包
        notify();
        System.out.println(Thread.currentThread().getName() + " consume a bread, size=" + list.size());
        return bread;
    }

}
