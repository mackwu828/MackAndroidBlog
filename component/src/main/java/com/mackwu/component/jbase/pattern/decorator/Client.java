package com.mackwu.component.jbase.pattern.decorator;

/**
 * ===================================================
 * Created by MackWu on 2021/7/13 16:29
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Client {

    public static void main(String[] args) {

        Component component = new ComponentImpl();
        component = new Decorator(component);
        component.funA();
    }

}
