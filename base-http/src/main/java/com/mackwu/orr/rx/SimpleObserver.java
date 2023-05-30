package com.mackwu.orr.rx;

import com.mackwu.orr.HttpResult;
import com.mackwu.orr.error.ErrorType;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * ===================================================
 * Created by MackWu on 2020/6/29 19:05
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class SimpleObserver<T> implements Observer<T>, HttpResult<T> {

    private Disposable disposable;

    @Override
    public void onSubscribe(final Disposable d) {
        this.disposable = d;
    }

    @Override
    public void onNext(T t) {
        if (null == t) {
            onFail(ErrorType.DATA_NULL_ERROR, "data is null");
            return;
        }
        if (t instanceof List && ((List<?>) t).isEmpty()) {
            onFail(ErrorType.DATA_LIST_NULL_ERROR, "data is empty");
            return;
        }
        onSuccess(t, "");
    }

    @Override
    public void onError(final Throwable e) {
        ErrorType errorType = ErrorType.UNKNOWN;
        String message = e.getMessage();
        if (e instanceof HttpException) {
            errorType = ErrorType.HTTP_ERROR;
        } else if (e instanceof SocketTimeoutException) {
            errorType = ErrorType.SOCKET_TIME_OUT;
        } else if (e instanceof RuntimeException) {
            errorType = ErrorType.RUNTIME_ERROR;
        } else if (e instanceof UnknownHostException) {
            errorType = ErrorType.UNKNOWN_HOST_ERROR;
        } else if (e instanceof UnknownServiceException) {
            errorType = ErrorType.UNKNOWN_SERVICE_ERROR;
        }
        onFail(errorType, message);
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }

    @Override
    public void onFail(ErrorType errorType, String errorMsg) {

    }

    private void dispose() {
        if (null != disposable && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
