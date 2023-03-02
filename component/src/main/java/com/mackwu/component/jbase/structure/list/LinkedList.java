package com.mackwu.component.jbase.structure.list;

/**
 * @author MackWu
 * @since 2022/9/7 15:40
 */
public interface LinkedList<E> {

    /**
     * 添加元素
     *
     * @param e element
     */
    void add(E e);

    /**
     * 移出并获取元素。移出的是尾节点。
     *
     * @return element
     */
    E remove();

    /**
     * 获取元素。获取的是尾节点。
     *
     * @return element
     */
    E get();

}
