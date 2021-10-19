package com.mackwu.component.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ===================================================
 * Created by MackWu on 2020/6/29 19:07
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class RxUtil {

    /**
     * 子线程切换到主线程
     */
    public static <T> ObservableTransformer<T, T> ioToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 绑定生命周期
     */
//    public static <T> AutoDisposeConverter<T> bindLifecycle(LifecycleOwner lifecycleOwner) {
//        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner));
//    }

}
