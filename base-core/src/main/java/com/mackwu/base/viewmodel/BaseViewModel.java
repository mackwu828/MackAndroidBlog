package com.mackwu.base.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

/**
 * @author MackWu
 * @since 2020/7/8 15:50
 */
public class BaseViewModel extends ViewModel implements IViewModel {

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onLifecycleChanged(@NonNull LifecycleOwner owner, @NonNull Lifecycle.Event event) {

    }

}
