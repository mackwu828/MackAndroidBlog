package com.mackwu.component.jbase.structure.list;

/**
 * @author MackWu
 * @since 2022/9/7 15:40
 */
public class SingleLinkedList<E> implements LinkedList<E> {

    Node<E> head;
    int size;

    @Override
    public void add(E e) {
        Node<E> node = new Node<>(e, null);
        if (head == null) {
            head = node;
        } else {
            // 连接到最后一个节点上
            Node<E> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        size++;
    }

    @Override
    public E remove() {
        if (head == null) {
            return null;
        }
        Node<E> temp = head;
        Node<E> prev = null;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        E e = temp.item;
        if (prev != null) {
            prev.next = null;
        }
        size--;
        return e;
    }

    @Override
    public E get() {
        if (head == null) {
            return null;
        }
        Node<E> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp.item;
    }


    public void remove(E e) {
        if (head == null) {
            return;
        }
        if (e.equals(head.item)) {
            head = head.next;
            size--;
            return;
        }
        Node<E> temp = head;
        Node<E> prev;
        for (; ; ) {
            prev = temp;
            temp = temp.next;
            if (e.equals(temp.item)) {
                prev.next = temp.next;
                size--;
                break;
            }
        }
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
