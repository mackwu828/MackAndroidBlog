package com.mackwu.orr.interceptor;

import android.os.Build;

import java.util.HashMap;

import okhttp3.HttpUrl;
import okhttp3.OkHttp;
import okhttp3.RequestBody;

/**
 * ===================================================
 * Created by MackWu on 2020/9/7 21:16
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class UserAgentHeaderInterceptor extends HeaderInterceptor {

    private String userAgent = "Android/" + Build.VERSION.CODENAME + " Okhttp/" + OkHttp.VERSION;

    @Override
    public HashMap<String, String> getHeaderMap(HttpUrl httpUrl, String method, RequestBody requestBody) {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("User-Agent", userAgent);
        return headerMap;
    }
}
