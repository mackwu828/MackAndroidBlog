package com.mackwu.component.jbase.pattern.factory.f2;

import com.mackwu.component.jbase.pattern.factory.bean.Product;

/**
 * @author MackWu
 * @since 2023/1/12 16:09
 */
public class OldFactory {

    public static Product createProduct1() {
        Product product = new Product();
        // 省略创建细节
        return product;
    }

    public static Product createProduct2() {
        Product product = new Product();
        // 省略创建细节
        return product;
    }

}
