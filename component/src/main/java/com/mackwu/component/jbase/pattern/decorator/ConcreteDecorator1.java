package com.mackwu.component.jbase.pattern.decorator;

/**
 * ===================================================
 * Created by MackWu on 2021/2/24 15:24
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ConcreteDecorator1 extends Decorator{
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        super.operate();
        method1();
    }

    private void method1() {
        System.out.println("method1 修饰");
    }
}
