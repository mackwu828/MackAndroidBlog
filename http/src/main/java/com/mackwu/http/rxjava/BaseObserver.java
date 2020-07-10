package com.mackwu.http.rxjava;

import com.mackwu.http.bean.BaseResponse;

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
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>>, IOnResult<T> {

    private Disposable disposable;

    @Override
    public void onSubscribe(final Disposable d) {
        this.disposable = d;
    }

    @Override
    public void onNext(final BaseResponse<T> response) {
        if (response.getError() != 0) {
            onFail("error: " + response.getError() + ", message: " + response.getMessage());
            return;
        }
        T data = response.getData();
        if (null == data) {
            onFail("data is null");
            return;
        }
        if (data instanceof List && ((List) data).isEmpty()) {
            onFail("data is empty");
            return;
        }
        onSuccess(data, response.getMessage());
    }

    @Override
    public void onError(final Throwable e) {
        String message = e.getMessage();

        if (e instanceof HttpException) {

        } else if (e instanceof SocketTimeoutException) {

        } else if (e instanceof RuntimeException) {

        } else if (e instanceof UnknownHostException) {

        } else if (e instanceof UnknownServiceException) {

        } else {

        }
        onFail(message);
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }

    private void dispose() {
        if (null != disposable && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
