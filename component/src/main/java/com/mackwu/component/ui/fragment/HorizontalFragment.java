package com.mackwu.component.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.leanback.widget.HorizontalGridView;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.base.fragment.BaseFragment;
import com.mackwu.base.recycler.SpaceItemDecoration;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.base.BaseFocusQuickAdapter;
import com.mackwu.component.databinding.FragmentVerticalBinding;
import com.mackwu.component.ui.widget.LeanbackActivity;
import com.mackwu.component.util.AnimUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2021/11/26 10:25
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class HorizontalFragment extends BaseFragment<BaseViewModel, FragmentVerticalBinding> {

    private final Handler handler = new Handler();

    private List<LeanbackActivity.LeanbackItem> subData;
    private SubAdapter subAdapter;
    private SpaceItemDecoration subSmallSpaceItemDecoration;
    private SpaceItemDecoration subBigSpaceItemDecoration;
    private ObjectAnimator subHorizontalGridViewScaleAnimator;

    @Override
    public void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // subData
        subData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            subData.add(new LeanbackActivity.LeanbackItem());
        }
        // subAdapter
        subAdapter = new SubAdapter();
        subAdapter.setList(subData);
        // horizontalGridViewSub
        binding.horizontalGridViewSub.setAdapter(subAdapter);
        subSmallSpaceItemDecoration = new SpaceItemDecoration();
        subSmallSpaceItemDecoration.addConfig(0, getResources().getDimensionPixelOffset(R.dimen.dp_144), 0, 0, 0);
        subSmallSpaceItemDecoration.addConfig(0, getResources().getDimensionPixelOffset(R.dimen.dp_30), 0, 0);
        binding.horizontalGridViewSub.addItemDecoration(subSmallSpaceItemDecoration);
        subBigSpaceItemDecoration = new SpaceItemDecoration();
        subBigSpaceItemDecoration.addConfig(0, getResources().getDimensionPixelOffset(R.dimen.dp_100), 0, 0, 0);
        subBigSpaceItemDecoration.addConfig(0, getResources().getDimensionPixelOffset(R.dimen.dp_21), 0, 0);
        // 焦点位置变更
        binding.horizontalGridViewSub.setWindowAlignment(HorizontalGridView.WINDOW_ALIGN_LOW_EDGE);
        binding.horizontalGridViewSub.setWindowAlignmentOffset(1);
        binding.horizontalGridViewSub.setWindowAlignmentOffsetPercent(HorizontalGridView.WINDOW_ALIGN_OFFSET_PERCENT_DISABLED);
        binding.horizontalGridViewSub.setItemAlignmentOffset(getResources().getDimensionPixelOffset(R.dimen._dp_144));
        binding.horizontalGridViewSub.setItemAlignmentOffsetPercent(0);

        // setOnItemFocusChangeListener
        subAdapter.setOnItemFocusChangeListener((v, hasFocus, position) -> {
            // 305/277=1.1; 408/370=1.1
            ObjectAnimator scaleAnimator = AnimUtil.getScaleAnimator(v, 1.1f, 1.1f, 0, v.getHeight() / 2f);
            if (hasFocus) {
                scaleAnimator.start();
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(() -> v.setPadding(0, 0, getResources().getDimensionPixelOffset(R.dimen.dp_21), 0), 40);
            } else {
                scaleAnimator.reverse();
                v.setPadding(0, 0, 0, 0);
            }
        });

        // 277/198=1.4; 370/264=1.4
        subHorizontalGridViewScaleAnimator = AnimUtil.getScaleAnimator(binding.horizontalGridViewSub, 1.36f, 1.4f,
                getResources().getDimensionPixelOffset(R.dimen.dp_100), getResources().getDimensionPixelOffset(R.dimen.dp_216));
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    public void startAnimator() {
        subHorizontalGridViewScaleAnimator.start();
        binding.horizontalGridViewSub.removeItemDecoration(subSmallSpaceItemDecoration);
        binding.horizontalGridViewSub.addItemDecoration(subBigSpaceItemDecoration);
        binding.horizontalGridViewSub.setItemAlignmentOffset(getResources().getDimensionPixelOffset(R.dimen._dp_100));
    }

    public void reverseAnimator() {
        subHorizontalGridViewScaleAnimator.reverse();
        binding.horizontalGridViewSub.removeItemDecoration(subBigSpaceItemDecoration);
        binding.horizontalGridViewSub.addItemDecoration(subSmallSpaceItemDecoration);
        binding.horizontalGridViewSub.setItemAlignmentOffset(getResources().getDimensionPixelOffset(R.dimen._dp_144));
    }

    public static HorizontalFragment newInstance(LeanbackActivity.LeanbackItem leanbackItem) {
        HorizontalFragment verticalFragment = new HorizontalFragment();
//        Bundle args = new Bundle();
//        args.putSerializable("objectType", objectType);
//        args.putSerializable("recommends", (ArrayList<Recommend>) recommends);
//        searchFragment.setArguments(args);
        return verticalFragment;
    }

    private static class SubAdapter extends BaseFocusQuickAdapter<LeanbackActivity.LeanbackItem, BaseViewHolder> {

        public SubAdapter() {
            super(R.layout.fragment_item_horizontal);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, LeanbackActivity.LeanbackItem data) {
            ImageView imageView = helper.getView(R.id.iv_sub);
        }
    }

}
