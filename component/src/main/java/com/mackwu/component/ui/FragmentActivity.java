package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mackwu.component.R;
import com.mackwu.component.ui.fragment.HomeFragment;
import com.mackwu.component.ui.fragment.UserFragment;
import com.mackwu.xmvc.BaseTransactionActivity;
import com.mackwu.xmvc.util.LogUtil;

import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2020/8/4 14:53
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class FragmentActivity extends BaseTransactionActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        replaceFragmentAddToBackStack(R.id.fl_container, HomeFragment.class);
        replaceFragmentAddToBackStack(R.id.fl_container, UserFragment.class);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtil.d("onBackPressed...");
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            LogUtil.d("fragment: " + fragment.getClass());
            if (fragment.getClass() == UserFragment.class) {
                LogUtil.d( "XXXX");
            }
        }
    }
}
