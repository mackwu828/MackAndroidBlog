package com.mackwu.component.jbase.structure.queue;

/**
 * @author MackWu
 * @since 2022/9/7 11:49
 */
public class DoubleQueue<E> implements Deque<E> {

    @Override
    public void addFirst(E e) {

    }

    @Override
    public void addLast(E e) {

    }

    private static class Node<E> {
        Node<E> prev;
        E item;
        Node<E> next;

        Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}
