package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivtiyHttpBinding;
import com.yhao.floatwindow.FloatWindow;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.dnsoverhttps.DnsOverHttps;
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

        binding.btnAsyncGet.setOnClickListener(v -> asyncGet("https://www.baidu.com/"));
    }

    private void asyncGet(String url) {
        LogUtil.d("asyncGet...");
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                }
            }
        });
    }

    private OkHttpClient createOkHttpClient() {
        long timeout = 10 * 1000;
        Cache appCache = new Cache(new File("cacheDir", "okhttpcache"), 10 * 1024 * 1024);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier((hostname, session) -> true)
                .cache(appCache);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addNetworkInterceptor(httpLoggingInterceptor);


        OkHttpClient okHttpClient = builder.build();

        try {
            DnsOverHttps dnsOverHttps = new DnsOverHttps.Builder()
                    .client(okHttpClient)
                    .url(new HttpUrl.Builder()
                            .scheme("https")
                            .host("1.1.1.1")
                            .query("dns-query")
                            .build()
                    )
                    .bootstrapDnsHosts(InetAddress.getByName("8.8.8.8"), InetAddress.getByName("8.8.4.4"))
                    .build();
            return okHttpClient.newBuilder()
                    .dns(dnsOverHttps)
                    .build();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return okHttpClient;
    }

}
