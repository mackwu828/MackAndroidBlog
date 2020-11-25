package com.mackwu.component;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.mackwu.xmvc.BaseActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.OnClick;

/**
 * ===================================================
 * Created by MackWu on 2020/11/4 15:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class SecondActivity extends BaseActivity {

    private ExecutorService mSingleInstallThread = Executors.newSingleThreadExecutor();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.btn_test)
    public void onBtnTestClicked() {
        mSingleInstallThread.execute(() -> {
            Log.d("TAG", "XXX");
        });
    }

}
