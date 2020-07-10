package com.mackwu.component;

import android.view.View;

import com.mackwu.component.base.BaseFragment;
import com.mackwu.component.fragment.FragmentActivity;
import com.mackwu.component.util.ActivityStartUtil;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ActivityFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_activity;
    }

    @Override
    public void initView(View view) {
        // test
        view.findViewById(R.id.btn_test).setOnClickListener(v -> ActivityStartUtil.startActivity(this, FragmentActivity.class));
    }
}
