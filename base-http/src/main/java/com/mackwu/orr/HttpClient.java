package com.mackwu.orr;

import com.mackwu.orr.interceptor.UserAgentHeaderInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author MackWu
 * @since 2020/9/7 18:17
 */
public class HttpClient {

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private final HashMap<String, Object> serviceMap = new HashMap<>();
    private final long timeout;
    private final String baseUrl;
    private final boolean openLog;
    private final List<Interceptor> interceptors;
    private final CookieJar cookieJar;

    public HttpClient(Builder builder) {
        this.timeout = builder.timeout;
        this.baseUrl = builder.baseUrl;
        this.openLog = builder.openLog;
        this.interceptors = builder.interceptors;
        this.cookieJar = builder.cookieJar;
        buildOkHttpClient();
        buildRetrofit();
    }

    public static class Builder {
        private long timeout;
        private String baseUrl;
        private boolean openLog;
        private List<Interceptor> interceptors;
        private CookieJar cookieJar;

        public Builder() {
            this.timeout = 15000;
            this.openLog = true;
        }

        public Builder timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder openLog(boolean openLog) {
            this.openLog = openLog;
            return this;
        }

        public Builder interceptors(List<Interceptor> interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public Builder cookieJar(CookieJar cookieJar) {
            this.cookieJar = cookieJar;
            return this;
        }

        public HttpClient build() {
            return new HttpClient(this);
        }
    }

    private void buildOkHttpClient() {
        // builder
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier((hostname, session) -> true);

        // addInterceptor
        if (interceptors != null && interceptors.size() > 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        // userAgentHeaderInterceptor
        UserAgentHeaderInterceptor userAgentHeaderInterceptor = new UserAgentHeaderInterceptor();
        builder.addInterceptor(userAgentHeaderInterceptor);

        // httpLoggingInterceptor
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (openLog) httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addNetworkInterceptor(httpLoggingInterceptor);

        // cookieJar
        if (cookieJar != null) {
            builder.cookieJar(cookieJar);
        }

        // okHttpClient
        okHttpClient = builder.build();
    }

    private void buildRetrofit() {
        // retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @SuppressWarnings("unchecked")
    public <S> S getApi(Class<S> serviceCls) {
        if (null == retrofit) return null;
        if (serviceMap.containsKey(serviceCls.getName())) {
            return (S) serviceMap.get(serviceCls.getName());
        }
        S s = retrofit.create(serviceCls);
        serviceMap.put(serviceCls.getName(), s);
        return s;
    }

}
