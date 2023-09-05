package com.mackwu.component.ui;


import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.ActivityStartUtil;
import com.mackwu.base.util.Logger;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityMainBinding;
import com.mackwu.component.databinding.WidgetActivityProgressBarBinding;
import com.mackwu.component.ui.recycler.RecyclerViewActivity;
import com.mackwu.component.ui.viewmodel.MainViewModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import timber.log.Timber;

/**
 * @author MackWu
 * @since 2020/6/19 23:34
 */
public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        ActivityStartUtil.startActivity(this, PhotoViewActivity.class);
//        ActivityStartUtil.startActivity(this, StickyHeaderActivity.class);
//        ActivityStartUtil.startActivity(this, VerticalGridViewActivity.class);
//        ActivityStartUtil.startActivity(this, TimelineGalleryActivity.class);

        binding.btnTest.setOnClickListener(v -> {

        });
        binding.btnTest2.setOnClickListener(v -> {
        });
    }

}
