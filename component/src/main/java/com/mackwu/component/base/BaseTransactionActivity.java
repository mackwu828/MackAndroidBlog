package com.mackwu.component.base;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * ===================================================
 * Created by MackWu on 2020/6/20 0:22
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseTransactionActivity extends BaseActivity implements ITransaction {

    private Handler handler = new Handler();
    private Runnable delayReplaceRunnable;

    protected Fragment instantiate(@NonNull final Class<? extends Fragment> clazz) {
        return getSupportFragmentManager().getFragmentFactory().instantiate(getClassLoader(), clazz.getName());
    }

    @Override
    public void replaceFragment(final int containerViewId, @NonNull final Class<? extends Fragment> clazz) {
        Fragment fragment = instantiate(clazz);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.commit();
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

}
