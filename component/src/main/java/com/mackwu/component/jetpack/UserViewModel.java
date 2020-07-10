package com.mackwu.component.jetpack;

import androidx.lifecycle.ViewModel;

import com.mackwu.component.util.LogUtil;

/**
 * ===================================================
 * Created by MackWu on 2020/6/30 11:50
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class UserViewModel extends ViewModel {

    /**
     * 在相关联的Activity或Fragment销毁时调用
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtil.d("onCleared...");
    }
}
