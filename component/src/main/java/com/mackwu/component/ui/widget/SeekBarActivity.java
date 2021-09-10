package com.mackwu.component.ui.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WidgetActivitySeekBarBinding;

/**
 * ===================================================
 * Created by MackWu on 2021/5/18 10:22
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class SeekBarActivity extends BaseActivity<BaseViewModel, WidgetActivitySeekBarBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        Drawable drawable = getNewDrawable();
        binding.seekBarBrightness.setThumb(drawable);
    }

    public BitmapDrawable getNewDrawable( ){
        Bitmap Bmp = BitmapFactory. decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap bmp = Bmp.createScaledBitmap(Bmp, 50, 50, true);
        BitmapDrawable d = new BitmapDrawable(bmp);
        Bitmap bitmap = d.getBitmap();
        if (bitmap.getDensity() == Bitmap.DENSITY_NONE) {
            d.setTargetDensity(getResources().getDisplayMetrics());
        }
        return d;
    }

}
