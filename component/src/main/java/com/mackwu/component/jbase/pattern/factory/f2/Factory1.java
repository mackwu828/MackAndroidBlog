package com.mackwu.component.jbase.pattern.factory.f2;

import com.mackwu.component.jbase.pattern.factory.bean.Product;

/**
 * @author MackWu
 * @since 2022/6/28 16:08
 */
public class Factory1 extends Factory {

    @Override
    public Product createProduct() {
        return new Product();
    }
}
