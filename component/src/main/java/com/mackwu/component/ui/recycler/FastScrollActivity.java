package com.mackwu.component.ui.recycler;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.base.BaseActivity;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.WidgetActivityFastScorllBinding;
import com.mackwu.component.ui.viewmodel.RecyclerViewModel;
import com.mackwu.fastscroll.FastScroller;
import com.mackwu.fastscroll.util.Logger;

/**
 * ===================================================
 * Created by MackWu on 2022/3/14 18:00
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class FastScrollActivity extends BaseActivity<RecyclerViewModel, WidgetActivityFastScorllBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // adapter
        FastScrollAdapter2 adapter = new FastScrollAdapter2();
        adapter.setList(viewModel.getSectionData());

        // recyclerView
//        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(0,
//                getResources().getDimensionPixelSize(R.dimen.dp_10),0,getResources().getDimensionPixelSize(R.dimen.dp_10));
//        binding.recyclerView.addItemDecoration(spaceItemDecoration);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);


        binding.recyclerView.post(() -> {
            Logger.d("computeVerticalScrollRange=" + binding.recyclerView.computeVerticalScrollRange());
            Logger.d("dp_80=" + getResources().getDimensionPixelSize(R.dimen.dp_80));
            Logger.d("dp_400=" + getResources().getDimensionPixelSize(R.dimen.dp_400));

            int i = getResources().getDimensionPixelSize(R.dimen.dp_80) * 3
                    + getResources().getDimensionPixelSize(R.dimen.dp_400) * 7;
            Logger.d("XXX=" + i);
        });


        initFastScroller();
    }

    private void initFastScroller() {
        Drawable verticalThumbDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.shape_fast_scroll_thumb, null);
        Drawable verticalTrackDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.shape_fast_scroll_track, null);
        Drawable horizontalThumbDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.shape_fast_scroll_thumb, null);
        Drawable horizontalTrackDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.shape_fast_scroll_track, null);
        if (verticalThumbDrawable == null || verticalTrackDrawable == null
                || horizontalThumbDrawable == null || horizontalTrackDrawable == null) {
            throw new IllegalArgumentException(
                    "Trying to set fast scroller without both required drawables.");
        }
        new FastScroller(binding.recyclerView, verticalThumbDrawable, verticalTrackDrawable,
                horizontalThumbDrawable, horizontalTrackDrawable,
                null,
                getResources().getDimensionPixelSize(R.dimen.fastscroll_default_thickness),
                getResources().getDimensionPixelSize(R.dimen.fastscroll_minimum_range),
                getResources().getDimensionPixelOffset(R.dimen.fastscroll_margin));
    }

    private static class FastScrollAdapter extends BaseQuickAdapter<RecycleItem, BaseViewHolder> {

        public FastScrollAdapter() {
            super(R.layout.layout_item_fast_scorll);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, RecycleItem item) {
            helper.setText(R.id.tv_test, item.getDate());
            if (item.getResId() != 0) helper.setImageResource(R.id.iv_image, item.getResId());
        }
    }

    private static class FastScrollAdapter2 extends BaseSectionQuickAdapter<RecycleItem, BaseViewHolder> {

        public FastScrollAdapter2() {
            super(R.layout.layout_item_fast_scorll_header);
            setNormalLayout(R.layout.layout_item_fast_scorll);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, RecycleItem item) {
            helper.setText(R.id.tv_test, item.getDate());
            if (item.getResId() != 0) {
                helper.setImageResource(R.id.iv_image, item.getResId());
            }
        }

        @Override
        protected void convertHeader(@NonNull BaseViewHolder helper, @NonNull RecycleItem item) {
//            helper.setText(R.id.tv_test, item.getDate());
        }
    }

}
