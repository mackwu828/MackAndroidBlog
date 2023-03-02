package com.mackwu.component.jbase.structure;

import android.annotation.SuppressLint;
import android.os.Build;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author MackWu
 * @since 2022/9/20 15:56
 */
public class CustomLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    /**
     * 获取头节点
     */
    public Entry<K, V> getHead() {
        Iterator<Entry<K, V>> iterator = entrySet().iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    /**
     * 获取尾节点。即最近添加的元素。
     */
    @SuppressWarnings({"unchecked", "JavaReflectionMemberAccess"})
    @SuppressLint("SoonBlockedPrivateApi")
    public Entry<K, V> getTail() {
        // 反射获取。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                Field tailFiled = LinkedHashMap.class.getDeclaredField("tail");
                tailFiled.setAccessible(true);
                return (Entry<K, V>) tailFiled.get(this);
            } catch (Exception ignored) {
            }
        }
        // 遍历获取。
        Entry<K, V> tail = null;
        for (Entry<K, V> entry : entrySet()) {
            tail = entry;
        }
        return tail;
    }

    /**
     * 移除并获取头节点
     */
    public V pollFirst() {
        Entry<K, V> head = getHead();
        if (head != null) {
            remove(head.getKey());
            return head.getValue();
        }
        return null;
    }

    /**
     * 移除并获取尾节点
     */
    public V pollLast() {
        Entry<K, V> tail = getTail();
        if (tail != null) {
            remove(tail.getKey());
            return tail.getValue();
        }
        return null;
    }

}
