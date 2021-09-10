package com.mackwu.component.pattern.decorator;

import com.mackwu.component.pattern.decorator.Component;
import com.mackwu.component.pattern.decorator.ConcreteComponent;
import com.mackwu.component.pattern.decorator.ConcreteDecorator1;
import com.mackwu.component.pattern.decorator.ConcreteDecorator2;

/**
 * ===================================================
 * Created by MackWu on 2021/7/13 16:29
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component = new ConcreteDecorator1(component);
        component = new ConcreteDecorator2(component);
        component.operate();
    }

}
