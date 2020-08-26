package com.mackwu.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.view.basic.EditTextActivity;
import com.mackwu.xmvc.BaseFragment;
import com.mackwu.xmvc.util.ActivityStartUtil;

import butterknife.OnClick;

public class BasicFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_basic;
    }

    @Override
    public void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.btn_test)
    public void onBtnTestClicked() {
    }

    @OnClick(R.id.btn_text_view)
    public void onBtnTextViewClicked() {
    }

    @OnClick(R.id.btn_edit_text)
    public void onBtnEditTextClicked() {
        ActivityStartUtil.startActivity(this, EditTextActivity.class);
    }

    @OnClick(R.id.btn_recycler_view)
    public void onBtnRecyclerViewClicked() {
    }
}
