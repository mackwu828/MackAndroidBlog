package com.mackwu.base.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;

/**
 * @author MackWu
 * @since 2022/12/15 18:46
 */
public abstract class LazyLoadFragment<VM extends BaseViewModel, B extends ViewBinding> extends BaseFragment<VM, B> {

    private boolean isPrepared;
    private boolean isFirstLoad = true;

    public abstract void loadData();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.d("onViewCreated...");
        isPrepared = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.d("setUserVisibleHint...");
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && isFirstLoad) {
            isFirstLoad = false;
            loadData();
        }
    }

}
