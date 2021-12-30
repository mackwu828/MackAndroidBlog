package com.mackwu.component.ui.widget;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.leanback.widget.HorizontalGridView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.recycler.SpaceItemDecoration;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.base.BaseFocusQuickAdapter;
import com.mackwu.component.databinding.WidgetActivityLeanbackBinding;
import com.mackwu.component.ui.fragment.HorizontalFragment;
import com.mackwu.component.ui.fragment.VerticalFragment;
import com.mackwu.component.util.AnimUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2021/11/17 16:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class LeanbackActivity extends BaseActivity<BaseViewModel, WidgetActivityLeanbackBinding> {

    private final Handler handler = new Handler();

    private List<LeanbackItem> mainData;
    private MainAdapter mainAdapter;
    private ObjectAnimator mainHorizontalGridViewScaleAnimator;
    private SpaceItemDecoration mainBigSpaceItemDecoration;
    private SpaceItemDecoration mainSmallSpaceItemDecoration;

    private VerticalFragment verticalFragment;
    private HorizontalFragment horizontalFragment;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // mainData
        mainData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LeanbackItem leanbackItem = new LeanbackItem();
            if (i % 2 == 0) {
                leanbackItem.imageStyle = 1;
            } else {
                leanbackItem.imageStyle = 0;
            }
            mainData.add(leanbackItem);
        }
        // mainAdapter
        mainAdapter = new MainAdapter();
        mainAdapter.setList(mainData);
        // horizontalGridViewMain
        binding.horizontalGridViewMain.setAdapter(mainAdapter);
        mainBigSpaceItemDecoration = new SpaceItemDecoration();
        mainBigSpaceItemDecoration.addConfig(0, getResources().getDimensionPixelOffset(R.dimen.dp_100), 0, 0, 0);
        mainBigSpaceItemDecoration.addConfig(0, getResources().getDimensionPixelOffset(R.dimen.dp_12), 0, 0);
        binding.horizontalGridViewMain.addItemDecoration(mainBigSpaceItemDecoration);
        mainSmallSpaceItemDecoration = new SpaceItemDecoration();
        mainSmallSpaceItemDecoration.addConfig(0, getResources().getDimensionPixelOffset(R.dimen.dp_100), 0, 0, 0);
        mainSmallSpaceItemDecoration.addConfig(0, getResources().getDimensionPixelOffset(R.dimen.dp_20), 0, 0);
        // 焦点位置变更
        binding.horizontalGridViewMain.setWindowAlignment(HorizontalGridView.WINDOW_ALIGN_LOW_EDGE);
        binding.horizontalGridViewMain.setWindowAlignmentOffset(1);
        binding.horizontalGridViewMain.setWindowAlignmentOffsetPercent(HorizontalGridView.WINDOW_ALIGN_OFFSET_PERCENT_DISABLED);
        binding.horizontalGridViewMain.setItemAlignmentOffset(getResources().getDimensionPixelOffset(R.dimen._dp_100));
        binding.horizontalGridViewMain.setItemAlignmentOffsetPercent(0);
        binding.horizontalGridViewMain.requestFocus();

        // setOnItemFocusChangeListener
        mainAdapter.setOnItemFocusChangeListener((v, hasFocus, position) -> {
            LogUtil.d("setOnItemFocusChangeListener...  hasFocus: " + hasFocus + ", position: " + position);
            // 583/530=1.1; 440/400=1.1
            v.post(() -> {
                ObjectAnimator scaleAnimator = AnimUtil.getScaleAnimator(v, 1.1f, 1.1f, 0, v.getHeight());
                if (hasFocus) {
                    scaleAnimator.start();
                    handler.removeCallbacksAndMessages(null);
                    // 583-530=53;
                    handler.postDelayed(() -> v.setPadding(0, 0, getResources().getDimensionPixelOffset(R.dimen.dp_24), 0), 40);
                } else {
                    scaleAnimator.reverse();
                    v.setPadding(0, 0, 0, 0);
                }
            });

            LeanbackItem item = mainAdapter.getItem(position);
            changeFragment(item);
        });
        // setOnItemClickListener
        mainAdapter.setOnItemClickListener((adapter, view, position) -> {
            LogUtil.d("setOnItemClickListener...  position: " + position);
        });

        // mainHorizontalGridViewScaleAnimator
        // 530/212=2.5; 400/160=2.5
        mainHorizontalGridViewScaleAnimator = AnimUtil.getScaleAnimator(binding.horizontalGridViewMain, 2.5f, 2.5f,
                getResources().getDimensionPixelOffset(R.dimen.dp_100), getResources().getDimensionPixelOffset(R.dimen._dp_66));
        mainHorizontalGridViewScaleAnimator.start();


        // addOnGlobalFocusChangeListener
        binding.llParentContainer.getViewTreeObserver().addOnGlobalFocusChangeListener((oldFocus, newFocus) -> {
            LogUtil.d("oldFocus: " + oldFocus + ", newFocus: " + newFocus);
            // horizontalGridViewMain is focused
            if (newFocus != null && newFocus.getId() == R.id.cl_main_container && (
                    oldFocus != null && oldFocus.getId() == R.id.cl_vertical_container ||
                            oldFocus != null && oldFocus.getId() == R.id.cl_horizontal_container
            )) {
                startMainAnimator();
                if (oldFocus.getId() == R.id.cl_vertical_container) {
                    verticalFragment.reverseAnimator();
                } else if (oldFocus.getId() == R.id.cl_horizontal_container) {
                    horizontalFragment.reverseAnimator();
                }

                binding.clFooter.setVisibility(View.GONE);
                binding.ivPanelBgN.setVisibility(View.VISIBLE);
                binding.ivPanelBgF.setVisibility(View.GONE);
            }
            // horizontalGridViewSub is focused
            if (oldFocus != null && oldFocus.getId() == R.id.cl_main_container && (
                    newFocus != null && newFocus.getId() == R.id.cl_vertical_container ||
                            newFocus != null && newFocus.getId() == R.id.cl_horizontal_container
            )) {
                reverseMainAnimator();
                if (newFocus.getId() == R.id.cl_vertical_container) {
                    verticalFragment.startAnimator();
                } else if (newFocus.getId() == R.id.cl_horizontal_container) {
                    horizontalFragment.startAnimator();
                }

                binding.clFooter.setVisibility(View.VISIBLE);
                binding.ivPanelBgN.setVisibility(View.GONE);
                binding.ivPanelBgF.setVisibility(View.VISIBLE);
            }
        });
    }

    private void startMainAnimator() {
        mainHorizontalGridViewScaleAnimator.start();
        binding.horizontalGridViewMain.removeItemDecoration(mainSmallSpaceItemDecoration);
        binding.horizontalGridViewMain.addItemDecoration(mainBigSpaceItemDecoration);
    }

    private void reverseMainAnimator() {
        mainHorizontalGridViewScaleAnimator.reverse();
        binding.horizontalGridViewMain.removeItemDecoration(mainBigSpaceItemDecoration);
        binding.horizontalGridViewMain.addItemDecoration(mainSmallSpaceItemDecoration);
    }

    private void changeFragment(LeanbackItem leanbackItem) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        if (leanbackItem.imageStyle == 1) {
            if (verticalFragment == null) {
                verticalFragment = VerticalFragment.newInstance(leanbackItem);
                transaction.add(R.id.fl_sub_container, verticalFragment);
            } else {
                transaction.show(verticalFragment);
            }
        }
        if (leanbackItem.imageStyle == 0) {
            if (horizontalFragment == null) {
                horizontalFragment = HorizontalFragment.newInstance(leanbackItem);
                transaction.add(R.id.fl_sub_container, horizontalFragment);
            } else {
                transaction.show(horizontalFragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (verticalFragment != null) transaction.hide(verticalFragment);
        if (horizontalFragment != null) transaction.hide(horizontalFragment);
    }

    private static class MainAdapter extends BaseFocusQuickAdapter<LeanbackItem, BaseViewHolder> {

        public MainAdapter() {
            super(R.layout.leanback_layout_item_main);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, LeanbackItem data) {
            ImageView imageView = helper.getView(R.id.iv_main);
            Glide.with(getContext())
                    .load("http://cache.zeasn.tv/acc/zeasn-saas-sp/sp/pic/1637726475957_0bc7eb90-35a6-40ed-9810-568487ea759c.jpg")
                    .override(getContext().getResources().getDimensionPixelOffset(R.dimen.dp_530),
                            getContext().getResources().getDimensionPixelOffset(R.dimen.dp_400))
                    .transform(new MultiTransformation<>(new CenterCrop(),
                            new RoundedCorners(getContext().getResources().getDimensionPixelSize(R.dimen.dp_12))))
                    .into(imageView);
        }
    }


    public static class LeanbackItem {
        public int imageStyle; // 0横图，1竖图
    }

}
