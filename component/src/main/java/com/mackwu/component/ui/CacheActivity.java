package com.mackwu.component.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;

import androidx.annotation.Nullable;

import com.jakewharton.disklrucache.DiskLruCache;
import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityCacheBinding;

/**
 * ===================================================
 * Created by MackWu on 2022/4/22 10:24
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class CacheActivity extends BaseActivity<BaseViewModel, ActivityCacheBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {


    }

    private void a() {
        // 应用最大可用内存。单位KB
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // 缓存的最大值
        int maxSize = maxMemory / 8;
        LruCache<String, Bitmap> memoryCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }
}
