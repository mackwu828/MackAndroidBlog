package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.bean.User;
import com.mackwu.component.databinding.ActivityLiveDataBinding;
import com.mackwu.component.ui.livedata.Str2LiveData;
import com.mackwu.component.ui.livedata.StrLiveData;
import com.mackwu.component.ui.livedata.UserLiveData;

/**
 * ===================================================
 * Created by MackWu on 2021/5/26 10:33
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class LiveDataActivity extends BaseActivity<BaseViewModel, ActivityLiveDataBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        LiveEventBus.get("111", String.class).observeSticky(this, LogUtil::d);
    }

}
