package com.mackwu.component.jbase.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ===================================================
 * Created by MackWu on 2022/4/29 17:28
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyQueue {

    public static void main(String[] args) {
//        testBlockingQueue();
    }

    private static void testBlockingQueue(){
        BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(3);
        // 增
        // add 添加数据时，如果队列已满，则会抛出IllegalStateException: Queue full
        // offer 添加数据时，成功返回true，失败返回false，如果队列满时返回false不会报错
        // put 添加数据时，如果队列已满，线程会被阻塞，等待队列有空余容量时再添加。
        linkedBlockingQueue.add("111");
        linkedBlockingQueue.add("222");
        linkedBlockingQueue.add("333");
//        boolean offer = linkedBlockingQueue.offer("111");
//        boolean offer1 = linkedBlockingQueue.offer("222");
//        boolean offer2 = linkedBlockingQueue.offer("333");
//        System.out.println(offer + ", " + offer1 + ", " + offer2);
//        new Thread(() -> {
//            try {
//                linkedBlockingQueue.put("111");
//                linkedBlockingQueue.put("222");
//                linkedBlockingQueue.put("333");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        // 删
        // remove: 删除数据，成功返回true，失败返回false
        // poll: 删除数据，当队列为空时，返回null
        // take: 删除数据，如果队列为空，线程会被阻塞
        // drainTo: 删除队列里的所有数据，并将数据移动到传入的集合中
//        linkedBlockingQueue.remove("111");
//        linkedBlockingQueue.poll();
//        try {
//            linkedBlockingQueue.take();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        List<String> list = new ArrayList<>();
        linkedBlockingQueue.drainTo(list);
        for (String s : list) {
            System.out.println("list:" + s);
        }


        // 查
        // element: 获取头元素，如果队列为空，则会抛出NoSuchElementException
        // peek: 获取头元素，如果队列为空，则会抛出NoSuchElementException
        String element = linkedBlockingQueue.element();
        String peek = linkedBlockingQueue.peek();
        for (String s : linkedBlockingQueue) {
            System.out.println("queue: " + s);
        }
    }

}
