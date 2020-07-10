package com.mackwu.http.okhttp;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * ===================================================
 * Created by MackWu on 2020/6/28 19:25
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseParamInterceptor implements Interceptor {

    /**
     * get param
     */
    abstract HashMap<String, String> getParamMap(HttpUrl httpUrl);

    @Override
    public Response intercept(final Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        HttpUrl httpUrl = request.url();

        if (method.equals("GET")) {
            return chain.proceed(buildGetRequest(request, httpUrl));
        }

        if (method.equals("POST")) {
            return chain.proceed(Objects.requireNonNull(buildPostRequest(request, httpUrl)));
        }

        return chain.proceed(request);
    }

    private Request buildGetRequest(Request request, HttpUrl httpUrl) {
        HttpUrl.Builder builder = request.url().newBuilder();
        // add base param
        HashMap<String, String> paramMap = getParamMap(httpUrl);
        for (String key : paramMap.keySet()) {
            builder.addEncodedQueryParameter(key, paramMap.get(key));
        }
        // create new request
        return request.newBuilder()
                .url(builder.build())
                .build();
    }

    private Request buildPostRequest(Request request, HttpUrl httpUrl) {

        if (request.body() instanceof FormBody) {
            FormBody.Builder builder = new FormBody.Builder();
            // add origin param
            FormBody formBody = (FormBody) request.body();
            for (int i = 0; i < formBody.size(); i++) {
                builder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
            }
            // add base param
            HashMap<String, String> paramMap = getParamMap(httpUrl);
            for (String key : paramMap.keySet()) {
                builder.addEncoded(key, Objects.requireNonNull(paramMap.get(key)));
            }
            // create new request
            return request.newBuilder()
                    .post(builder.build())
                    .build();
        }

        if (request.body() instanceof MultipartBody) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            // add origin param
            MultipartBody multipartBody = (MultipartBody) request.body();
            for (final MultipartBody.Part part : multipartBody.parts()) {
                builder.addPart(part);
            }
            // add base param
            HashMap<String, String> paramMap = getParamMap(httpUrl);
            for (String key : paramMap.keySet()) {
                builder.addFormDataPart(key, Objects.requireNonNull(paramMap.get(key)));
            }
            // create new request
            return request.newBuilder()
                    .post(builder.build())
                    .build();
        }

        // json
        Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<>();
        // add origin param
        try {
            Buffer buffer = new Buffer();
            Objects.requireNonNull(request.body()).writeTo(buffer);
            map.putAll(gson.fromJson(buffer.readUtf8(), HashMap.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // add base param
        map.putAll(getParamMap(httpUrl));
        return request.newBuilder()
                .post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), gson.toJson(map)))
                .build();
    }

}
