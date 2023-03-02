package com.mackwu.component.jbase.structure.queue;

/**
 * @author MackWu
 * @since 2022/9/6 11:20
 */
public class MessageQueue<E> {

    private Node<E> head;
    private int size;

    /**
     * 添加元素。按时间排序，头节点的时间最短。
     * @param e 元素
     * @param when 时间
     */
    public void add(E e, long when) {
        Node<E> node = new Node<>(e, when);
        if (head == null) {
            node.next = null;
            head = node;
            size++;
            return;
        }
        // 如果该元素时间比头节点小，则设置为头节点
        if (when < head.when) {
            node.next = head;
            head = node;
            size++;
            return;
        }
        // 按时间排序，头节点的时间最小
        Node<E> temp = head;
        Node<E> prev;
        for (; ; ) {
            prev = temp;
            temp = temp.next;
            if (temp == null || when < temp.when) {
                break;
            }
        }
        node.next = temp;
        prev.next = node;
        size++;
    }

    /**
     * 获取并移出头元素。
     */
    public E poll() {
        Node<E> node = head;
        if (node == null) {
            return null;
        }
        head = node.next;
        node.next = null;
        size--;
        return node.item;
    }

    /**
     * 获取头元素。
     */
    public E get() {
        return head == null ? null : head.item;
    }

    public int size() {
        return size;
    }

    private static class Node<E>{
        E item;
        Node<E> next;
        long when;

        public Node(E item, long when) {
            this.item = item;
            this.when = when;
        }
    }
}
