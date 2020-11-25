package com.mackwu.xmvc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mackwu.xmvc.util.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseActivity extends AppCompatActivity implements IActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("onCreate...  What activity is created? " + this.getClass().getSimpleName());
        setContentView(getLayoutId());
        initUnBinder();
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    private void initUnBinder() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
