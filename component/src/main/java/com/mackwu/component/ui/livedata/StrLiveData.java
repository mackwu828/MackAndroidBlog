package com.mackwu.component.ui.livedata;

import androidx.lifecycle.MutableLiveData;

import com.mackwu.base.util.LogUtil;


/**
 * ===================================================
 * Created by MackWu on 2020/12/31 18:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class StrLiveData extends MutableLiveData<String> {

    private static StrLiveData instance;

    private StrLiveData() {
    }

    public static StrLiveData getInstance() {
        if (instance == null) {
            instance = new StrLiveData();
        }
        return instance;
    }

    @Override
    protected void onActive() {
        super.onActive();
        LogUtil.d("onActive...");
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        LogUtil.d("onInactive...");
    }

}
