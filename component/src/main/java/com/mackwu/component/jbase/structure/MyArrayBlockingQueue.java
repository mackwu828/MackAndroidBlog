package com.mackwu.component.jbase.structure;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author MackWu
 * @since 2022/6/14 17:27
 */
public class MyArrayBlockingQueue {

    int[] items;

    public void enqueue() {

    }

    public void dequeue() {

    }

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.add(1);
        try {
            arrayBlockingQueue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
