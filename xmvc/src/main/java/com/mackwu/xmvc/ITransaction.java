package com.mackwu.xmvc;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * ===================================================
 * Created by MackWu on 2020/6/20 0:20
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public interface ITransaction {

    /**
     * replace
     * <p>
     * java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
     */
    void replaceFragment(@IdRes int containerViewId, @NonNull final Class<? extends Fragment> clazz);

    /**
     * replace
     */
    void replaceFragment(@IdRes int containerViewId, @NonNull final Fragment fragment);

    /**
     * replace and add to back stack
     */
    void replaceFragmentAddToBackStack(@IdRes int containerViewId, @NonNull final Class<? extends Fragment> clazz);

    /**
     * replace and add to back stack
     */
    void replaceFragmentAddToBackStack(@IdRes int containerViewId, @NonNull final Fragment fragment);

    /**
     * delay replace
     */
    void delayReplaceFragment(@IdRes int containerViewId, @NonNull final Class<? extends Fragment> clazz, long delayMillis);

    /**
     * pop
     */
    void popFragment();

    /**
     * pop
     */
    void popFragment(String name);

    /**
     * pop all
     */
    void popAllFragment();

    /**
     * show
     */
    void showFragment(@IdRes int containerViewId, @NonNull final Class<? extends Fragment> clazz);

    /**
     * hide
     */
    void hideFragment(@NonNull final Class<? extends Fragment> clazz);
}
