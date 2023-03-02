package com.mackwu.component.ui;

import static com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.ORIENTATION_0;

import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.mackwu.base.BaseActivity;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivitySubsamplingScaleBinding;
import com.mackwu.component.ui.viewmodel.MainViewModel;

/**
 * ===================================================
 * Created by MackWu on 2022/3/9 10:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class SubsamplingScaleActivity extends BaseActivity<MainViewModel, ActivitySubsamplingScaleBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        SubsamplingScaleImageView scaleImageView = binding.scaleImageView;
        scaleImageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        scaleImageView.setZoomEnabled(false);


        // maxScale
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.long_1, options);
        int imgWidth = options.outWidth;
        int imgHeight = options.outHeight;
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        float maxScale = screenWidth * 1f / imgWidth;

        // setMaxScale
        scaleImageView.setMaxScale(maxScale);
        // setImage
        ImageViewState imageViewState = new ImageViewState(maxScale, new PointF(imgWidth / 2f, imgHeight / 2f), ORIENTATION_0);
        scaleImageView.setImage(ImageSource.resource(R.drawable.long_1), imageViewState);


        binding.btnTest.setOnClickListener(v -> {
            scaleImageView.setVisibility(View.GONE);
            binding.ivTest.setVisibility(View.VISIBLE);
//            Rect rect = new Rect(0, 0, screenWidth, screenHeight);
//            Bitmap b = decoder.decodeRegion(rect, 1);
//            LogUtil.d("B=" + b);
//            binding.ivTest.setImageBitmap(b);
        });
    }


}