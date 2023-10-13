package com.mackwu.component.ui;

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

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        WebSettings settings = binding.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        binding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        binding.webView.loadUrl("https://www.google.com/maps/place/San+Francisco%2C+CA%2C+USA");
    }

}