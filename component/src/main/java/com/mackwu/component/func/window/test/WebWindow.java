package com.mackwu.component.func.window.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mackwu.base.util.Logger;
import com.mackwu.component.ComponentApp;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WindowWebBinding;
import com.mackwu.component.func.window.FloatWindow;
import com.mackwu.component.util.ProcessUtil;

import java.util.Objects;

/**
 * ===================================================
 * Created by MackWu on 2021/11/10 10:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WebWindow {

    private static final String TAG = WebWindow.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private static WebWindow instance;
    private final Context context;
    private WebView webView;

    private WebWindow() {
        this.context = ComponentApp.getInstance();
        Logger.d("WebWindow init...  " + ProcessUtil.getProcessInfo(context));
        initWindow(context);
        initView();
    }

    public static WebWindow getInstance() {
        if (instance == null) {
            instance = new WebWindow();
        }
        return instance;
    }

    @SuppressLint("InflateParams")
    private void initWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.window_web, null);
        WindowWebBinding bind = WindowWebBinding.bind(view);
        webView = bind.webView;
        FloatWindow.with(context)
                .tag(TAG)
                .view(view)
                .width(400)
                .height(400)
                .type(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
                .build();
    }

    private void initView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
        initSettings();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initSettings() {
        // webSettings
        WebSettings webSettings = webView.getSettings();
        // 允许JS代码
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        // 设置5.0以上开启混合模式加载
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        // 允许Dom缓存
        webSettings.setDomStorageEnabled(true);
        // 允许访问File
        webSettings.setAllowFileAccess(true);
        // 允许自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        // 设置布局算法
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
    }

    public void show() {
        Logger.d("show...  " + ProcessUtil.getProcessInfo(context) + ", webView=" + webView);
        if (webView != null) webView.post(() -> Objects.requireNonNull(FloatWindow.get(TAG)).show());
    }

    public void hide() {
        Logger.d("hide...  " + ProcessUtil.getProcessInfo(context));
        if (webView != null) webView.post(() -> Objects.requireNonNull(FloatWindow.get(TAG)).hide());
    }

    public void loadUrl() {
        Logger.d("loadUrl...  " + ProcessUtil.getProcessInfo(context));
        if (webView != null) webView.post(() -> webView.loadUrl("https://www.baidu.com/"));
    }

}
