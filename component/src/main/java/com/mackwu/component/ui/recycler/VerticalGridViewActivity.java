package com.mackwu.component.ui.recycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.ActivityLeanbackBinding;
import com.mackwu.component.func.focus.BaseFocusQuickAdapter;
import com.mackwu.component.ui.view.QuickViewHolder;
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

        // verticalGridView
//        binding.verticalGridView.setWindowAlignment(HorizontalGridView.WINDOW_ALIGN_LOW_EDGE);
//        binding.verticalGridView.setWindowAlignmentOffset(0);
//        binding.verticalGridView.setWindowAlignmentOffsetPercent(HorizontalGridView.WINDOW_ALIGN_OFFSET_PERCENT_DISABLED);
//        binding.verticalGridView.setItemAlignmentOffset(getResources().getDimensionPixelOffset(R.dimen.dp_50) * -1);
//        binding.verticalGridView.setItemAlignmentOffsetPercent(0);

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
