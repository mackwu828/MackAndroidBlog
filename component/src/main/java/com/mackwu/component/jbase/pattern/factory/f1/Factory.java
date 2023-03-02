package com.mackwu.component.jbase.pattern.factory.f1;

import com.mackwu.component.jbase.pattern.factory.bean.Product;

import java.lang.reflect.Constructor;

/**
 * @author MackWu
 * @since 2023/1/11 17:57
 */
public class Factory {

    public static Product createProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("product");
        return product;
    }

    public static <T extends Product> T createProduct(Class<T> c) {
        T product = null;
        try {
            Constructor<T> constructor = c.getConstructor();
            product = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

}
