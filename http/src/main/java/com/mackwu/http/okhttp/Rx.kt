package com.mackwu.http.okhttp

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * ===================================================
 * Created by MackWu on 2019/11/4 15:50
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {
    return ObservableTransformer { upstream ->
        upstream.subscribeOn(Schedulers.io()) // 指定上面没有指定所在线程的Observable在IO线程执行
                .observeOn(AndroidSchedulers.mainThread()) // 指定下面的call在主线程中执行
    }
}
