package com.mackwu.component.ui.recycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.ActivitySmartRefreshBinding;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

import java.util.List;

/**
 * @author MackWu
 * @since 2022/9/20 17:27
 */
public class SmartRefreshActivity extends BaseActivity<RecyclerViewModel, ActivitySmartRefreshBinding> {

    MyAdapter adapter;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        // adapter
        adapter = new MyAdapter(viewModel.getData());
        // recyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        // refreshLayout
        binding.refreshLayout.setEnableRefresh(false);
        binding.refreshLayout.setFooterHeight(0);
        binding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            Logger.d("setOnLoadMoreListener...");
            adapter.addData(viewModel.getData());
            refreshLayout.finishLoadMore(true);
        });
    }

    static class MyAdapter extends BaseQuickAdapter<RecycleItem, BaseViewHolder> {

        public MyAdapter(List<RecycleItem> data) {
            super(R.layout.item_test_1, data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, RecycleItem item) {
            helper.setText(R.id.tv_test, item.getDate());
            helper.setImageResource(R.id.iv_test, item.getResId());
        }
    }
}
