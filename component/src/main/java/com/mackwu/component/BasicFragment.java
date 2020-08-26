package com.mackwu.component;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.component.ui.FragmentActivity;
import com.mackwu.component.ui.ServiceActivity;
import com.mackwu.xmvc.BaseFragment;
import com.mackwu.xmvc.util.ActivityStartUtil;

import butterknife.OnClick;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class BasicFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_activity;
    }

    @Override
    public void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

    @OnClick(R.id.btn_test)
    public void onViewClicked() {
        ActivityStartUtil.startActivity(this, ServiceActivity.class);
    }

    @OnClick(R.id.btn_style_activity)
    public void onBtnActivityStyleClicked() {
    }

}
