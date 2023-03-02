package com.mackwu.component.jbase.structure.queue;

/**
 * @author MackWu
 * @since 2022/9/7 15:27
 */
public class SingleQueue<E> implements Queue<E> {

    @Override
    public void add(E e) {

    }

    @Override
    public void remove(E e) {

    }

    public void enqueue() {

    }

    public void dequeue() {

    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
