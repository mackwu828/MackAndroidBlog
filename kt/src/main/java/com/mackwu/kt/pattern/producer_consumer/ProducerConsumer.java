package com.mackwu.kt.pattern.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ===================================================
 * Created by MackWu on 2020/4/23 16:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ProducerConsumer {

    private static final int MAX_LEN = 10;
    private static final Queue<Integer> queue = new LinkedList<>();
    private static boolean flag = true;

    public void produce() {
        while (flag) {
            synchronized (queue) {
                // 当前队列已满
                while (queue.size() == MAX_LEN) {
                    queue.notify();
                    System.out.println("生产者生产任务... 当前队列已满");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 生产者生产一个任务
                queue.add(1);
                queue.notify();
                System.out.println("生产者生产一个任务，当前队列长度为: " + queue.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void consume() {
        while (flag) {
            synchronized (queue) {
                // 当前队列为空
                while (queue.size() == 0) {
                    queue.notify();
                    System.out.println("消费者消费任务... 当前队列为空");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 消费者消费一个任务
                queue.poll();
                queue.notify();
                System.out.println("消费者消费一个任务，当前队列长度为: " + queue.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stop(){
        flag = false;
    }

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        new Thread(producerConsumer::produce).start();
        new Thread(producerConsumer::consume).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producerConsumer.stop();
    }
}
