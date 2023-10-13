package com.mackwu.component.ui;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityPhotoViewBinding;
import com.mackwu.component.ui.viewmodel.MainViewModel;
import com.mackwu.component.util.ByteUtil;

import java.security.MessageDigest;

/**
 * @author MackWu
 * @since 2023/9/5 11:53
 */
public class PhotoViewActivity extends BaseActivity<MainViewModel, ActivityPhotoViewBinding> {

    //            String url = Environment.getExternalStorageDirectory().getPath() + "/ZWhalePhoto/resource/45cdce85-c70b-4f0f-9391-53351fafb6f0.jpg";
//    String url = Environment.getExternalStorageDirectory().getPath() + "/ZWhalePhoto/resource/31b34534-22e7-4f4a-901a-40c2f498611b.jpg";
    String url = Environment.getExternalStorageDirectory().getPath() + "/ZWhalePhoto/resource/0231698e-49b5-43a2-a66b-97901fb23669.jpg";
//    String url = Environment.getExternalStorageDirectory().getPath() + "/ZWhalePhoto/resource/15c1323b-284c-4929-9bc2-f355333684c0.jpg";
    Matrix imageMatrix;
    float scaleFactor;
    private float dx;
    private float focusX;
    private float focusY;
    private float dy;
    private String matrixString;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> {
            Glide.with(this)
                    .asBitmap()
                    .fitCenter()
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
            Matrix matrix = new Matrix();
//            float scaleFactor = 2.5421522f;
//            float dx = 1027.4473f;
//            float dy = 424.44946f;
            // focusX=1142.1078, focusY=425.79776
            matrix.postScale(scaleFactor, scaleFactor);
            matrix.postTranslate(dx, dy);
            binding.photoView.setDisplayMatrix(matrix);
        });
        binding.btnTest3.setOnClickListener(v -> {
            Glide.with(this)
                    .asBitmap()
                    .load(url)
                    .fitCenter()
                    .transform(new BitmapTransformation() {
                        @Override
                        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                            Matrix matrix = new Matrix();
                            matrix.postScale(scaleFactor, scaleFactor);
                            matrix.postTranslate(dx, dy);
                            //
                            Bitmap targetBitmap = pool.get(getScreenWidth(),
                                    getResources().getDimensionPixelSize(R.dimen.dp_1080), Bitmap.Config.ARGB_4444);
                            //
                            Canvas canvas = new Canvas(targetBitmap);
                            canvas.drawBitmap(toTransform, matrix, null);
                            return targetBitmap;
                        }

                        @Override
                        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

                        }
                    })
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
        binding.photoView.setOnMatrixChangeListener(rect -> {

        });

        binding.photoView.setOnScaleChangeListener((scaleFactor, focusX, focusY, dx, dy) -> {
            this.scaleFactor = scaleFactor;
            this.focusX = focusX;
            this.focusY = focusY;
            this.dx = dx;
            this.dy = dy;
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
