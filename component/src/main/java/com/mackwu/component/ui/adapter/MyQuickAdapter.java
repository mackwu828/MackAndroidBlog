package com.mackwu.component.ui.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.component.R;

/**
 * ===================================================
 * Created by MackWu on 2022/2/25 10:33
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyQuickAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyQuickAdapter() {
        super(R.layout.layout_item);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String s) {
        helper.setText(R.id.tv_test, s);
    }

}
