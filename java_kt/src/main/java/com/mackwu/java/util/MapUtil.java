package com.mackwu.java.util;

import java.util.Iterator;
import java.util.Map;

/**
 * ===================================================
 * Created by MackWu on 2020/6/29 10:47
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MapUtil {

    public static void traverse1(Map map){
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
        }
    }

    public static void traverse2(Map map){
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Object value = map.get(key);
        }
    }

    public static void traverse3(Map map){
        for (final Object o : map.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            Object key = entry.getKey();
            Object value = entry.getValue();
        }
    }

    public static void traverse4(Map map){
        for (final Object key : map.keySet()) {
            Object value = map.get(key);
        }
    }
}
