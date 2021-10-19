package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseTransactionActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityFragmentBinding;

/**
 * ===================================================
 * Created by MackWu on 2021/3/16 17:07
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class FragmentActivity extends BaseTransactionActivity<BaseViewModel, ActivityFragmentBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
//        replaceFragment(R.id.fl_container, HomeFragment.class);
    }
}
