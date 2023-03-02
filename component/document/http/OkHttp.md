

使用？https://blog.csdn.net/lmj623565791/article/details/47911083
拦截器？okhttp内部定义了五个拦截器：RetryAndFollowUpInterceptor、BridgeInterceptor、CacheInterceptor、ConnectInterceptor、CallServerInterceptor
自定义拦截器？日志拦截、cookie拦截、基本参数拦截、缓存拦截
连接池？ConnectionPool



OkHttp + Retrofit + Rxjava?
OkHttp + Retrofit + Rxjava如何防止内存泄漏？
Okhttp + Retrofit + Coroutine?
Okhttp + Retrofit + Coroutine如何防止内存泄漏？保存job的list，onDestroy里遍历取消job
OkHttp + DNS？https://github.com/square/okhttp/blob/858939f025068c6d3581338317bba03e63ebd909/okhttp-dnsoverhttps/README.md


后台返回不规范的数据如非json数据如何解析？修改ResponseBody？https://stackoverflow.com/questions/35773028/how-to-change-body-in-okhttp-response


设备系统时间不对时请求https接口会报SSLHandshakeException？https://www.jianshu.com/p/29183f81286e?utm_campaign=maleskine...&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
javax.net.ssl.SSLHandshakeException: com.android.org.bouncycastle.jce.exception.ExtCertPathValidatorException: Could not validate certificate: Certificate not valid until Thu Jul 16 20:25:27 GMT+08:00 2020 (compared to Fri Mar 02 14:23:46 GMT+08:00 2018)


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
```
 
