package com.mackwu.component.jbase.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author MackWu
 * @since 2022/7/5 10:35
 */
public class DynamicProxy implements InvocationHandler {

    // 委托类
    Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("before");
//        Object invoke = method.invoke(obj, args);
//        System.out.println("after");
//        return invoke;
        if (Vendor.getInstance().isInit()) {
            return method.invoke(obj, args);
        }
        return proxy;
    }

    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy(Vendor.getInstance());
        // 动态生成代理。动态生成的代理类是中介类的代理
        Sell sell = (Sell) Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[]{Sell.class}, dynamicProxy);
        sell.sell();
        sell.ad();
    }

}
