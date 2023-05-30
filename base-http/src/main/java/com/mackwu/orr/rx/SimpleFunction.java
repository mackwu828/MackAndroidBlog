package com.mackwu.orr.rx;

import com.mackwu.orr.error.ErrorType;
import com.mackwu.orr.error.ErrorException;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * ===================================================
 * Created by MackWu on 2020/7/2 11:52
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class SimpleFunction<T, R> implements Function<T, ObservableSource<R>> {

    public abstract ObservableSource<R> applyFinal(T data);

    @Override
    public ObservableSource<R> apply(final T t) {
        if (null == t) {
            return Observable.error(new ErrorException(ErrorType.DATA_NULL_ERROR, "data is null"));
        }
        if (t instanceof List && ((List<?>) t).isEmpty()) {
            return Observable.error(new ErrorException(ErrorType.DATA_LIST_NULL_ERROR, "data is empty"));
        }
        return applyFinal(t);
    }

}
