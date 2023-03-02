package com.mackwu.component.ui.recycler;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.base.BaseActivity;
import com.mackwu.component.ui.view.util.decoration.GridSpaceItemDecoration;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.WidgetActivityRecyclerGridBinding;

/**
 * @author MackWu
 * @since 2022/7/26 10:43
 */
public class RecyclerGridActivity extends BaseActivity<BaseViewModel, WidgetActivityRecyclerGridBinding> {

    private RecyclerView.ItemDecoration landScapeItemDecoration;
    private RecyclerView.ItemDecoration portraitItemDecoration;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
//        // adapter
//        adapter = new MyGridAdapter();
//        // data
//        adapter.setList(viewModel.getSimpleData());
//        // recyclerView
//        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        // setAdapter
//        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        onOrientationChanged();
    }

    private void onOrientationChanged() {
        if (landScapeItemDecoration == null) {
            landScapeItemDecoration = new GridSpaceItemDecoration(4,
                    getResources().getDimensionPixelOffset(R.dimen.dp_20), getResources().getDimensionPixelOffset(R.dimen.dp_20));
        }
        if (portraitItemDecoration == null) {
            portraitItemDecoration = new GridSpaceItemDecoration(3,
                    getResources().getDimensionPixelOffset(R.dimen.dp_20), getResources().getDimensionPixelOffset(R.dimen.dp_20));
        }
        // 横屏
        if (isOrientationLandscape()) {
            binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            binding.recyclerView.removeItemDecoration(portraitItemDecoration);
            binding.recyclerView.addItemDecoration(landScapeItemDecoration);
        }
        // 竖屏
        else {
            binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            binding.recyclerView.removeItemDecoration(landScapeItemDecoration);
            binding.recyclerView.addItemDecoration(portraitItemDecoration);
        }
    }

    public class MyAdapter extends BaseQuickAdapter<RecycleItem, BaseViewHolder> {

        public MyAdapter() {
            super(R.layout.layout_item_grid);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, RecycleItem item) {
//        ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
//        layoutParams.height = isOrientationLandscape() ? getContext().getResources().getDimensionPixelOffset(R.dimen.dp_300) :
//                getContext().getResources().getDimensionPixelOffset(R.dimen.dp_200);
//        helper.itemView.setLayoutParams(layoutParams);
            helper.setText(R.id.tv_test, item.getDate());
            helper.setImageResource(R.id.iv_image, item.getResId());
        }

    }

}
