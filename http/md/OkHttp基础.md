An HTTP client for Android, Kotlin, and Java

为什么okhttp返回了http400走了onResponse，而retrofit+rxjava返回了http400走了onError

## 资料
GitHub官网：https://github.com/square/okhttp
官网：https://square.github.io/okhttp/
鸿洋 OkHttp完全解析：https://blog.csdn.net/lmj623565791/article/details/47911083
鸿洋 Https相关完全解析：https://blog.csdn.net/lmj623565791/article/details/48129405

## 初始化
```
    /**
     * 初始化
     * connectTimeout: 连接超时时间
     * readTimeout: 读取超时时间
     * writeTimeout: 写入超时时间
     * retryOnConnectionFailure: 超时重试
     * hostnameVerifier: 信任所有证书
     * cookieJar: cookie持久化
     * addInterceptor: 拦截器
     */
    fun init(context: Context) {
        okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier { _, _ -> true }
                .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))) // cookieJar
                .addInterceptor(HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BODY }) // httpLoggingInterceptor
                .build()
    }
```


## get


## post


## 拦截器
okhttp内部定义了五个拦截器：RetryAndFollowUpInterceptor、BridgeInterceptor、CacheInterceptor、ConnectInterceptor、CallServerInterceptor
自定义拦截器：
日志拦截、cookie拦截、基本参数拦截、缓存拦截


## 连接池
ConnectionPool

 
