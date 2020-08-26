package com.mackwu.java.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class GenericUtil {

    public static Class<?> getActualTypeArgument(Class<?> cls) {
        Class<?> actualTypeArgument = null;
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                actualTypeArgument = (Class<?>) actualTypeArguments[0];
            }
        }
        return actualTypeArgument;
    }

}
