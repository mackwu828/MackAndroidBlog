package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivtiyHttpBinding;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * ===================================================
 * Created by MackWu on 2021/4/21 19:24
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class HttpActivity extends BaseActivity<BaseViewModel, ActivtiyHttpBinding> {

    OkHttpClient client = createOkHttpClient();

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        binding.btnGet.setOnClickListener(v -> get("https://www.baidu.com/"));
    }

    private void get(String url) {
        Logger.d("get...");
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Logger.d("onFailure...  " + e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Logger.d("onResponse...  " + response.body().string());
                }
            }
        });
    }

    private OkHttpClient createOkHttpClient() {
        long timeout = 6 * 1000;

        // httpLoggingInterceptor
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // okHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier((hostname, session) -> true)
                .addNetworkInterceptor(httpLoggingInterceptor)
//                .dns(new HappyDns())
                .build();

        return okHttpClient;
    }

}
