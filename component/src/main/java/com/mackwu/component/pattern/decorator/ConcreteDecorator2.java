package com.mackwu.component.pattern.decorator;

/**
 * ===================================================
 * Created by MackWu on 2021/2/24 15:24
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ConcreteDecorator2 extends Decorator{
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        super.operate();
        method2();
    }

    private void method2() {
        System.out.println("method2 修饰");
    }
}
