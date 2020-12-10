package com.mackwu.component.ui;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.mackwu.component.R;
import com.mackwu.component.util.ActivityStartUtil;
import com.mackwu.mvvm.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_test)
    ImageView ivTest;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable final Bundle savedInstanceState) {
        ActivityStartUtil.startActivity(this, LiveDataActivity.class);
    }

    @Override
    public void initData(@Nullable final Bundle savedInstanceState) {
    }

    @OnClick(R.id.btn_test)
    public void onBtnTestClicked() {
    }

}
