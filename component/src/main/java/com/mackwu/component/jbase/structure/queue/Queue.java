package com.mackwu.component.jbase.structure.queue;

/**
 * @author MackWu
 * @since 2022/9/7 11:48
 */
public interface Queue<E> {

    void add(E e);

    void remove(E e);
}
