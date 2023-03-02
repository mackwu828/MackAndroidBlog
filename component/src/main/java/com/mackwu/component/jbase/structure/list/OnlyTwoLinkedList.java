package com.mackwu.component.jbase.structure.list;

/**
 * @author MackWu
 * @since 2022/9/7 15:40
 */
public class OnlyTwoLinkedList<E> {

    Node<E> head;

    public void add(E e) {
        Node<E> node = new Node<>(e, null);
        if (head == null) {
            head = node;
        } else {
            head.next = node;
        }
    }

    public E getHead() {
        return head != null ? head.item : null;
    }

    public E getTail() {
        if (head != null) {
            Node<E> tail = head.next;
            return tail != null ? tail.item : null;
        }
        return null;
    }

    public boolean isEmpty() {
        return head != null;
    }

    public void clear() {
        if (head != null) {
            head.next = null;
        }
        head = null;
    }

    static class Node<E> {
        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
