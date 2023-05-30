package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;
import com.mackwu.component.databinding.ActivityOverlapBinding;
import com.mackwu.component.func.anim.FocusAnimHelper;
import com.mackwu.component.func.focus.BaseFocusQuickAdapter;
import com.mackwu.component.ui.view.MatrixLayout;
import com.mackwu.component.ui.view.QuickViewHolder;
import com.mackwu.component.func.decoration.SpaceItemDecoration;
import com.mackwu.component.ui.viewmodel.OverlapViewModel;

/**
 * @author MackWu
 * @since 2023/3/31 16:36
 */
public class OverlapActivity extends BaseActivity<OverlapViewModel, ActivityOverlapBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // adapter
        MyAdapter adapter = new MyAdapter();
        adapter.setOnItemClickListener((a, view, position) -> {

        });
        adapter.setOnItemFocusChangeListener((view, hasFocus, position) -> {
            FocusAnimHelper.overlapAnimateFocus(view, hasFocus);
            // 获取焦点的View层级最高，显示在最前面
            view.setElevation(hasFocus ? 3 : position * 0.5f);
        });
        adapter.setList(viewModel.getData());

        // recyclerView
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration();
        spaceItemDecoration.setRect(0, 0, 0, 0, 0);
        spaceItemDecoration.setRect(-getResources().getDimensionPixelOffset(R.dimen.dp_125), 0, 0, 0);
        binding.recyclerView.addItemDecoration(spaceItemDecoration);
        binding.recyclerView.setAdapter(adapter);
    }

    private static class MyAdapter extends BaseFocusQuickAdapter<RecycleItem, QuickViewHolder> {
        public MyAdapter() {
            super(R.layout.item_overlap);
        }

        // 第一个位置旋转角度
        public static final float START_DEGREES = -15f;
        public static final float END_DEGREES = 0;

        // 第一个位置缩放大小
        public static final float START_SCALE = 0.76f;
        public static final float END_SCALE = 1f;

        @Override
        protected void convert(@NonNull QuickViewHolder viewHolder, RecycleItem recycleItem) {
            viewHolder.setImageResource(R.id.iv_test, recycleItem.getResId());
            int position = viewHolder.getItemPosition();
            // VerticalGridView前面的item层级大于后面的item，导致前面的item会覆盖后面的item
            // setElevation设置阴影可以提高层级，使后面item覆盖前面item
            viewHolder.getItemView().setElevation(position * 0.5f);
            //
            float degrees = getDegrees(position);
            float scale = getScale(position);
            MatrixLayout matrixLayout = viewHolder.getView(R.id.matrix_layout);
            matrixLayout.setDegrees(degrees);
            matrixLayout.setSx(scale);
            matrixLayout.setSy(scale);
            Logger.d("position=" + position + ", degrees=" + degrees + ", scale=" + scale);
        }

        private float getDegrees(int position) {
            int itemCount = getItemCount();
            float average = (END_DEGREES - START_DEGREES) / (itemCount - 1);
            return START_DEGREES + average * position;
        }

        private float getScale(int position) {
            int itemCount = getItemCount();
            float average = (END_SCALE - START_SCALE) / (itemCount - 1);
            return START_SCALE + average * position;
        }
    }

}
