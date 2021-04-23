package com.mackwu.component.ui.widget;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.WidgetActivityWebBinding;

/**
 * ===================================================
 * Created by MackWu on 2020/12/18 10:57
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WebActivity extends BaseActivity<BaseViewModel, WidgetActivityWebBinding> {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        // webSettings
        WebSettings webSettings = binding.webView.getSettings();
        // 允许JS代码
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置自适应屏幕，两者合用
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
        // 设置5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 允许Dom缓存
        webSettings.setDomStorageEnabled(true);
        // 允许App缓存
        webSettings.setAppCacheEnabled(true);
        // 允许访问File
        webSettings.setAllowFileAccess(true);
        // 允许自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        // 设置布局算法
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);

        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        binding.webView.loadUrl("https://www.youtube.com/watch?v=XWhZDQkq0bw");
    }

}