package com.mackwu.component;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.mackwu.component.base.BaseActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MainActivity extends BaseActivity {

    private FragmentPagerItemAdapter adapter;
    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable final Bundle savedInstanceState) {
        //
        viewPager = findViewById(R.id.view_pager);
        smartTabLayout = findViewById(R.id.smart_tab_layout);

        //
        FragmentPagerItems fragmentPagerItems = FragmentPagerItems.with(this)
                .add("Activity", ActivityFragment.class)
                .add("Jetpack", JetpackFragment.class)
                .create();
        adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), fragmentPagerItems);
        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);
    }

    @Override
    public void initData(@Nullable final Bundle savedInstanceState) {

    }

}
