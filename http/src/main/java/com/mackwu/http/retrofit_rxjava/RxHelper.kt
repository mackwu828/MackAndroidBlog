package com.mackwu.http.retrofit_rxjava

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * ===================================================
 * Created by MackWu on 2019/10/28 16:58
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
/**
 * 线程控制
 */
fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {
    return ObservableTransformer { upstream ->
        upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}


/**
 * Observable<T>.subscribe
 */
fun <T> Observable<T>.subscribeNext(action: (t: T) -> Unit) = subscribeReal(onNext = action)
fun <T> Observable<T>.subscribeError(action: (e: Throwable) -> Unit) = subscribeReal(onError = action)
fun <T> Observable<T>.subscribeReal(
        onNext: ((t: T) -> Unit)? = null,
        onError: ((e: Throwable) -> Unit)? = null
) {
    val observer = object : Observer<T> {

        private var disposable: Disposable? = null

        override fun onComplete() {
            disposable?.dispose()
        }

        override fun onSubscribe(d: Disposable) {
            disposable = d
        }

        override fun onNext(t: T) {
            onNext?.invoke(t)
        }

        override fun onError(e: Throwable) {
            onError?.invoke(e)
            disposable?.dispose()
        }

    }
    compose(rxSchedulerHelper())
            .subscribe(observer)
}