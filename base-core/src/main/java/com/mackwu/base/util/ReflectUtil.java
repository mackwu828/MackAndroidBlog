package com.mackwu.base.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ===================================================
 * Created by MackWu on 2021/4/21 19:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * Reflect Util
 * 反射工具类。
 */
public final class ReflectUtil {

    /**
     * 获取class对象泛型的class对象。
     * <code>
     * public class A<B, C> {
     * }
     * Class<B> bCls = getActualTypeArgument(A.class, 0)
     * Class<C> cCls = getActualTypeArgument(A.class, 1)
     * </code>
     *
     * @param cls   class对象
     * @param index 第几个泛型
     */
    public static Class<?> getActualTypeArgument(Class<?> cls, int index) {
        Class<?> actualTypeArgument = null;
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            if (actualTypeArguments.length > index) {
                actualTypeArgument = (Class<?>) actualTypeArguments[index];
            }
        }
        return actualTypeArgument;
    }
    
}
