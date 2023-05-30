package com.mackwu.orr.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * ===================================================
 * Created by MackWu on 2020/6/29 11:27
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 请求头拦截器
 */
public abstract class HeaderInterceptor implements Interceptor {

    /**
     * 获取header
     */
    public abstract HashMap<String, String> getHeaderMap(HttpUrl httpUrl, String method, RequestBody requestBody);

    @NonNull
    @Override
    public Response intercept(final Chain chain) throws IOException {
        Request request = chain.request();
        Headers.Builder builder = request.headers().newBuilder();
        HttpUrl httpUrl = request.url();
        String method = request.method();
        RequestBody requestBody = request.body();
        // add header
        HashMap<String, String> headerMap = getHeaderMap(httpUrl, method, requestBody);
        for (String key : headerMap.keySet()) {
            builder.add(key, Objects.requireNonNull(headerMap.get(key)));
        }
        // create new request
        Request newRequest = request.newBuilder()
                .headers(builder.build())
                .build();
        return chain.proceed(newRequest);
    }

}
