package com.mackwu.base;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.mackwu.base.viewmodel.BaseViewModel;


/**
 * ===================================================
 * Created by MackWu on 2020/6/20 0:22
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseTransactionActivity<VM extends BaseViewModel, B extends ViewBinding> extends BaseActivity<VM, B> implements ITransaction {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable delayReplaceRunnable;

    @Override
    public void replaceFragment(final int containerViewId, @NonNull final Class<? extends Fragment> clazz) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerViewId, instantiate(clazz))
                .commit();
    }

    @Override
    public void replaceFragment(int containerViewId, @NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerViewId, fragment)
                .commit();
    }

    @Override
    public void replaceFragmentAddToBackStack(int containerViewId, @NonNull Class<? extends Fragment> clazz) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(clazz.getSimpleName())
                .replace(containerViewId, instantiate(clazz))
                .commit();
    }

    @Override
    public void replaceFragmentAddToBackStack(int containerViewId, @NonNull Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.replace(containerViewId, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void delayReplaceFragment(final int containerViewId, @NonNull final Class<? extends Fragment> clazz, long delayMillis) {
        if (null == delayReplaceRunnable) {
            delayReplaceRunnable = () -> replaceFragment(containerViewId, clazz);
        }
        handler.removeCallbacks(delayReplaceRunnable);
        handler.postDelayed(delayReplaceRunnable, delayMillis);
    }

    @Override
    public void popFragment() {
        getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void popFragment(String name) {
        getSupportFragmentManager().popBackStackImmediate(name, 1);
    }

    @Override
    public void popAllFragment() {
        getSupportFragmentManager().popBackStackImmediate(null, 1);
    }

    @Override
    public void showFragment(final int containerViewId, @NonNull final Class<? extends Fragment> clazz) {
        Fragment fragment = instantiate(clazz);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(containerViewId, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commit();
    }

    @Override
    public void hideFragment(@NonNull final Class<? extends Fragment> clazz) {
        Fragment fragment = instantiate(clazz);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isVisible()) {
            transaction.hide(fragment);
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() <= 0) finish();
    }

    private Fragment instantiate(@NonNull final Class<? extends Fragment> clazz) {
        return getSupportFragmentManager().getFragmentFactory().instantiate(getClassLoader(), clazz.getName());
    }
}
