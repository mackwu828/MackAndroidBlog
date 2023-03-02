package com.mackwu.component.jbase.pattern.factory;

import com.mackwu.component.jbase.pattern.factory.bean.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MackWu
 * @since 2022/6/29 15:05
 */
public class LateInitFactory {

    private static final Map<String, Product> productMap = new HashMap<>();

    public static synchronized Product createProduct(String type) {
        Product product = null;
        if (productMap.containsKey(type)) {
            product = productMap.get(type);
        } else {
            if (type.equals("Product1")) {
//                product = new Product1();
            } else {
//                product = new Product2();
            }
//            productMap.put(type, product);
        }
        return product;
    }
}
