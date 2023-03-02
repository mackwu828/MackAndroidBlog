package com.mackwu.component.ui.recycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.ActivityLeanbackBinding;
import com.mackwu.component.ui.view.focus.BaseFocusQuickAdapter;
import com.mackwu.component.ui.view.recycler.QuickViewHolder;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;

import java.util.List;

/**
 * @author MackWu
 * @since 2022/7/6 15:36
 */
public class VerticalGridViewActivity extends BaseActivity<RecyclerViewModel, ActivityLeanbackBinding> {

    MyAdapter adapter;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // adapter
        adapter = new MyAdapter(viewModel.getData());
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Logger.d("setOnItemClickListener...  position=" + position);
        });
        adapter.setOnItemFocusChangeListener((view, hasFocus, position) -> {
            Logger.d("setOnItemFocusChangeListener...  hasFocus=" + hasFocus +", position=" + position);
            view.setActivated(hasFocus);
        });

        // verticalGridView
        binding.verticalGridView.setAdapter(adapter);



//        binding.verticalGridView.getViewTreeObserver().addOnGlobalFocusChangeListener((oldFocus, newFocus) -> {
//            LogUtil.d("oldFocus=" + oldFocus + ", newFocus=" + newFocus);
//        });
    }

    static class MyAdapter extends BaseFocusQuickAdapter<RecycleItem, QuickViewHolder> {

        public MyAdapter(List<RecycleItem> data) {
            super(R.layout.layout_item_leanback, data);
        }

        @Override
        protected void convert(@NonNull QuickViewHolder helper, RecycleItem item) {
            helper.setText(R.id.tv_test, item.getDate());
            helper.setImageResource(R.id.iv_image, item.getResId());
        }

    }

}
