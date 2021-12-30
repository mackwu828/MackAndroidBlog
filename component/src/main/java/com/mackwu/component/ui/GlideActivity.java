package com.mackwu.component.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityGlideBinding;
import com.mackwu.component.util.svg.GlideOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2021/12/22 15:48
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class GlideActivity extends BaseActivity<BaseViewModel, ActivityGlideBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

//        List<Integer> data = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            data.add(R.drawable.ic_proj_brightness);
//        }
//        MyAdapter adapter = new MyAdapter();
//        adapter.setList(data);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        binding.recyclerView.setLayoutManager(linearLayoutManager);
//        binding.recyclerView.setAdapter(adapter);

        long lastTime = System.currentTimeMillis();
        Glide.with(this)
                .load(R.drawable.ic_proj_brightness)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        LogUtil.d("cost: " + (System.currentTimeMillis() - lastTime));
                        binding.ivTest.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    private static class MyAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

        public MyAdapter() {
            super(R.layout.layout_item_glide);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, Integer i) {
//            helper.setImageResource(R.id.iv_icon, i);
            long lastTime = System.currentTimeMillis();
            Glide.with(getContext())
                    .load(i)
                    .apply(new GlideOptions()
                            .override(getContext().getResources().getDimensionPixelOffset(R.dimen.dp_112),
                                    getContext().getResources().getDimensionPixelOffset(R.dimen.dp_112))
                            .transform(new CenterCrop(), new RoundedCorners(getContext().getResources().getDimensionPixelOffset(R.dimen.dp_4)))
                    )
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            LogUtil.d("cost: " + (System.currentTimeMillis() - lastTime));
                            helper.setImageDrawable(R.id.iv_icon, resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }
    }

}
