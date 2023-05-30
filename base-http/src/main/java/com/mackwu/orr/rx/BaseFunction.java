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
public abstract class BaseFunction<T, R> implements Function<BaseResponse<T>, ObservableSource<BaseResponse<R>>> {

    public abstract ObservableSource<BaseResponse<R>> applyFinal(T data);

    @Override
    public ObservableSource<BaseResponse<R>> apply(final BaseResponse<T> response) {
        if (response.getError() != 0) {
            return Observable.error(new ErrorException(ErrorType.CODE_ERROR, "error: " + response.getError() + ", message: " + response.getMessage()));
        }
        T data = response.getData();
        if (null == data) {
            return Observable.error(new ErrorException(ErrorType.DATA_NULL_ERROR, "data is null"));
        }
        if (data instanceof List && ((List) data).isEmpty()) {
            return Observable.error(new ErrorException(ErrorType.DATA_LIST_NULL_ERROR, "data is empty"));
        }
        return applyFinal(data);
    }

}
