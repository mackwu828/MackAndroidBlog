package com.mackwu.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.mackwu.xmvc.BaseActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.smart_tab_layout)
    SmartTabLayout smartTabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable final Bundle savedInstanceState) {
        // data
        FragmentPagerItems fragmentPagerItems = FragmentPagerItems.with(this)
                .add("basic", BasicFragment.class)
                .create();
        // adapter
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), fragmentPagerItems);
        // viewPager
        viewPager.setAdapter(adapter);
        // smartTabLayout
        smartTabLayout.setViewPager(viewPager);
    }

    @Override
    public void initData(@Nullable final Bundle savedInstanceState) {

    }

}
