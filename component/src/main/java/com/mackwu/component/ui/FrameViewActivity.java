package com.mackwu.component.ui;

import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityFrameViewBinding;
import com.mackwu.component.func.LocalDataManager;
import com.mackwu.image.scale.CenterCropTransformation;
import com.mackwu.image.scale.FitCenterDownsample;
import com.mackwu.image.scale.PendingScaleType;
import com.mackwu.image.scale.util.MatrixUtil;
import com.mackwu.image.scale.util.ScaleLogger;

/**
 * @author MackWu
 * @since 2023/9/9 14:44
 */
public class FrameViewActivity extends BaseActivity<BaseViewModel, ActivityFrameViewBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> {
            fitCenter();
        });
        binding.btnTest2.setOnClickListener(v -> {
            centerCrop();
        });
        binding.btnTest3.setOnClickListener(v -> {

        });
        binding.btnTest4.setOnClickListener(v -> {

        });

        binding.frameView.setFrameMatrixChangedListener(matrix -> {
            if (isOrientationLandscape()) {
                LocalDataManager.getInstance().setScaleMatrix(matrix);
            } else {
                LocalDataManager.getInstance().setScaleVerticalMatrix(matrix);
            }
        });
    }

    private Matrix getMatrix() {
        Matrix matrix;
        if (isOrientationLandscape()) {
            matrix = LocalDataManager.getInstance().getScaleMatrix();
            if (matrix == null) {
                Matrix scaleVerticalMatrix = LocalDataManager.getInstance().getScaleVerticalMatrix();
                if (scaleVerticalMatrix != null) {
                    float scale = MatrixUtil.getScale(scaleVerticalMatrix);
                    matrix = new Matrix();
                    matrix.postScale(scale, scale);
                }
            }
        } else {
            matrix = LocalDataManager.getInstance().getScaleVerticalMatrix();
            if (matrix == null) {
                Matrix scaleMatrix = LocalDataManager.getInstance().getScaleMatrix();
                if (scaleMatrix != null) {
                    float scale = MatrixUtil.getScale(scaleMatrix);
                    matrix = new Matrix();
                    matrix.postScale(scale, scale);
                }
            }
        }
        return matrix;
    }

    int width;
    int height;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        binding.frameView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.frameView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (binding.frameView.getPendingScaleType() == PendingScaleType.FIT_CENTER) {
                    fitCenter();
                } else {
                    centerCrop();
                }
            }
        });
    }

    private void fitCenter() {
        ScaleLogger.d("fitCenter XXXXXX");
        Glide.with(this)
                .load(R.drawable.a)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .downsample(new FitCenterDownsample(binding.frameView))
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        binding.frameView.setImageDrawable(resource);
                        binding.frameView.setPendingScaleType(PendingScaleType.FIT_CENTER);
                        Matrix matrix = getMatrix();
                        ScaleLogger.d(String.format("fitCenter...  (%s, %s), (%s, %s, %s)", resource.getIntrinsicWidth(), resource.getIntrinsicHeight(),
                                MatrixUtil.getScale(matrix), MatrixUtil.getTransX(matrix), MatrixUtil.getTransY(matrix)));
                        if (matrix == null) {
                            binding.frameView.initScaleMatrix();
                        } else {
                            binding.frameView.updateScaleMatrix(matrix);
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    private void centerCrop() {
        RequestOptions requestOptions = new RequestOptions()
                .skipMemoryCache(true)
                .transform(new CenterCropTransformation(this, null));
        RequestBuilder<Drawable> thumbnail = Glide.with(this)
                .asDrawable()
                .sizeMultiplier(0.2f);
        Glide.with(this)
                .load(R.drawable.a)
                .apply(requestOptions)
                .thumbnail(thumbnail)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        binding.frameView.setImageDrawable(resource);
                        binding.frameView.setPendingScaleType(PendingScaleType.CENTER_CROP);
                        Matrix matrix = getMatrix();
                        ScaleLogger.d(String.format("centerCrop...  (%s, %s), (%s, %s, %s)", resource.getIntrinsicWidth(), resource.getIntrinsicHeight(),
                                MatrixUtil.getScale(matrix), MatrixUtil.getTransX(matrix), MatrixUtil.getTransY(matrix)));
                        if (matrix == null) {
                            binding.frameView.initScaleMatrix();
                        } else {
                            binding.frameView.updateScaleMatrix(matrix);
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

}
