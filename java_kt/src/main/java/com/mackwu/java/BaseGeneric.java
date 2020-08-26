package com.mackwu.java;

import com.mackwu.java.util.GenericUtil;

public class BaseGeneric<T> {

    protected T t;

    protected Class<T> getTCls(){
       return (Class<T>) GenericUtil.getActualTypeArgument(getClass());
    }
}
