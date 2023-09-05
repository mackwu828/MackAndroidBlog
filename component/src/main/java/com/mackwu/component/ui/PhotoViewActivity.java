package com.mackwu.component.ui;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.OnScaleChangedListener;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.component.databinding.ActivityPhotoViewBinding;
import com.mackwu.component.ui.viewmodel.MainViewModel;
import com.mackwu.component.util.ByteUtil;

/**
 * @author MackWu
 * @since 2023/9/5 11:53
 */
public class PhotoViewActivity extends BaseActivity<MainViewModel, ActivityPhotoViewBinding> {

    Matrix imageMatrix;
    float scaleFactor;
    private float dx;
    private  float dy;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        binding.photoView.setOnMatrixChangeListener(rect -> {
            // 483.4098, -962.8019, 1436.5903, 1103.4474
            //
            imageMatrix = binding.photoView.getImageMatrix();
            Logger.d("onMatrixChanged...  " + imageMatrix.toString());
            this.dx = rect.top;
            this.dy = rect.left;
        });
        binding.photoView.setOnScaleChangeListener((scaleFactor, focusX, focusY) -> {
            this.scaleFactor = scaleFactor;
        });
        binding.btnTest.setOnClickListener(v -> {
            String url = Environment.getExternalStorageDirectory().getPath() + "/ZWhalePhoto/resource/cbd3c30c-53d6-4208-97d6-fa199a67646d.jpg";
            Glide.with(this)
                    .asBitmap()
                    .load(url)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .listener(new RequestListener<>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                            Logger.d(ByteUtil.getByteCount(resource));
                            return false;
                        }
                    })
                    .into(binding.photoView);
        });
        binding.btnTest2.setOnClickListener(v -> {
            String url = Environment.getExternalStorageDirectory().getPath() + "/ZWhalePhoto/resource/cbd3c30c-53d6-4208-97d6-fa199a67646d.jpg";
            Glide.with(this)
                    .asBitmap()
                    .load(url)
                    .skipMemoryCache(true)
                    .transform(new ScaleTransform(getScreenWidth(), getScreenHeight(), scaleFactor, dx, dy))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .listener(new RequestListener<>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                            Logger.d(ByteUtil.getByteCount(resource));
                            return false;
                        }
                    })
                    .into(binding.photoView);
        });
        binding.btnTest3.setOnClickListener(v -> {
            binding.photoView.setImageDrawable(null);
        });
    }

}
