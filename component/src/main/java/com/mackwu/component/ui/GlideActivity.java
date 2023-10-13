package com.mackwu.component.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityGlideBinding;
import com.mackwu.image.scale.FitCenterDownsample;
import com.mackwu.image.scale.FitCenterTransformation;

/**
 * @author MackWu
 * @since 2022/10/8 11:20
 */
public class GlideActivity extends BaseActivity<BaseViewModel, ActivityGlideBinding> {

    //    String url = "http://cache.zeasn.tv/dev/whale-photo-mgr-api/mgr/file/1663743232999_7fecb606-7f81-4282-9dc8-8591d85deb16.png";
    String url = "https://www.baidu.com/img/bd_logo1.png";

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> {

        });
        binding.btnTest2.setOnClickListener(v -> {
        });
        binding.ivTest.setScaleType(ImageView.ScaleType.CENTER);
        Glide.with(this)
                .load(url)
                .skipMemoryCache(true)
//                .transform(new FitCenterTransformation())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        Logger.d(String.format("onResourceReady...  %s, %s", resource.getIntrinsicWidth(), resource.getIntrinsicHeight()));
                        return false;
                    }
                })
                .into(binding.ivTest);
    }

    private void a() {
        Glide.with(this)
                .load(url)
                // 设置占位图。图片加载成功前显示。
                .placeholder(R.drawable.ic_launcher_background)
                // 设置异常占位图。图片加载失败时显示。
                .error(R.drawable.ic_launcher_background)
                // 设置缩略图。图片加载成功前显示。
                .thumbnail(0.5f)
                // 设置图片大小。如果原图过大，加载到小的ImageView上，会浪费内存。压缩原图成ImageView的大小。
                .override(300, 300)
                // 是否禁用内存缓存
                .skipMemoryCache(true)
                // 设置磁盘缓存策略。
                // DiskCacheStrategy.NONE 表示不启用磁盘缓存
                // DiskCacheStrategy.RESOURCE 表示只缓存转换后的图片
                // DiskCacheStrategy.All 表示既缓存原始图片，也缓存转换后的图片
                // DiskCacheStrategy.DATA 表示只缓存原始图片
                // DiskCacheStrategy.AUTOMATIC 根据数据源自动选择磁盘缓存策略。默认。
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                // 设置加载监听
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(binding.ivTest);
    }

    private void b() {
        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        binding.ivTest.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

}
