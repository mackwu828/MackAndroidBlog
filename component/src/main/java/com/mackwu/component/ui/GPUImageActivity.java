package com.mackwu.component.ui;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityBinderBinding;
import com.mackwu.image.transition.util.Logger;

import java.util.Random;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageTransformFilter;

/**
 * @author MackWu
 * @since 2023/3/14 14:04
 */
public class GPUImageActivity extends BaseActivity<BaseViewModel, ActivityBinderBinding> {

    GPUImage gpuImage;
    int[] resourceIds = new int[]{
            R.drawable.bg_home,
            R.drawable.home_bg_1,
            R.drawable.home_bg_2,
            R.drawable.home_bg_3,
    };
    Random random = new Random();
    Handler handler = new Handler();

    int percentage;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (percentage > 100) {
                percentage = 0;
                return;
            }
            Logger.d("XXXX  percentage=" + percentage);
            float[] transform = new float[16];
            Matrix.setRotateM(transform, 0, (360f * percentage / 100), 0f, 0f, 1.0f);
            gpuImageFilter.setTransform3D(transform);
//            binding.gpuIv.requestRender();
            percentage++;
            handler.postDelayed(runnable, 20);
        }
    };

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    GPUImageTransformFilter gpuImageFilter;

    private float range(int percentage, float start, float end){
        return (end - start) * percentage / 100 + start;
    }

    private Uri getUri(int resourceId) {
        Resources r = getResources();
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resourceId) + "/"
                + r.getResourceTypeName(resourceId) + "/"
                + r.getResourceEntryName(resourceId));
    }
}
