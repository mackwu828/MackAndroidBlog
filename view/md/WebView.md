
ScrollView中嵌套WebView，WebView高度为0无法自适应。继承WebView，重写onMeasure计算其高度：
https://www.jianshu.com/p/e753efc98231  WebView高度设置为wrap_content

CookieManager同步cookie：http://www.jianshu.com/p/40c767312103
接收证书，在onReceivedSslError去掉super，添加handler.proceed()

优化：资源本地化，缓存，延迟加载和js：http://www.jianshu.com/p/ebffa8a61e51
https://blog.csdn.net/android_freshman/article/details/79461671

webview替代方案：腾讯x5内核、Crosswalk

## 基础