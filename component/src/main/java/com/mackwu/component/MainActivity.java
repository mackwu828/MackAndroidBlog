package com.mackwu.component;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.mackwu.xmvc.BaseActivity;

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
//        ActivityStartUtil.startActivity(this, LottieActivity.class);
        Glide.with(this)
                .asGif()
                .load(R.raw.music_loading)
                .into(ivTest);
    }

    @Override
    public void initData(@Nullable final Bundle savedInstanceState) {
    }

    public void start(View view) {
    }

    @OnClick(R.id.btn_test)
    public void onBtnTestClicked() {
    }

}
