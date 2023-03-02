package com.mackwu.component.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityDrawerBinding;

/**
 * @author MackWu
 * @since 2022/12/1 14:27
 */
public class DrawerActivity extends BaseActivity<BaseViewModel, ActivityDrawerBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initDrawer();
        binding.btnTest1.setOnClickListener(v -> {
            Logger.d("btnTest1");
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        });
        binding.btnTest2.setOnClickListener(v -> {
            Logger.d("btnTest2");
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        });
    }

    private void initDrawer(){
        // 抽屉滑出时背景颜色。默认灰色。
        binding.drawerLayout.setScrimColor(Color.TRANSPARENT);
        // 监听抽屉事件
        binding.drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }


}
