package com.mackwu.component.pattern;

/**
 * ===================================================
 * Created by MackWu on 2020/12/10 14:30
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class JavaEarlySingleton {

//    private static JavaEarlySingleton instance = new JavaEarlySingleton();
//
//    private JavaEarlySingleton() {
//    }
//
//    public static JavaEarlySingleton getInstance() {
//        return instance;
//    }

    private JavaEarlySingleton() {
    }

    private static class SingletonHolder{
        private static final JavaEarlySingleton INSTANCE = new JavaEarlySingleton();
    }

    public static JavaEarlySingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
