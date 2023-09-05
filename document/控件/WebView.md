

WebSettings配置？
WebView怎么加载assets中的html文件？
WebView怎么和JS交互？包括WebView调用JS、JS调用WebView 

什么是WebView内核？
如何查看WebView内核版本？
系统WebView路径在哪？包名是什么？下载地址是什么？


ScrollView中嵌套WebView，WebView高度为0无法自适应？继承WebView，重写onMeasure计算其高度
WebView如何同步cookie？http://www.jianshu.com/p/40c767312103。接收证书，在onReceivedSslError去掉super，添加handler.proceed()
WebView有哪些替代方案？腾讯x5内核、Crosswalk
WebView如何通过网页调试？ https://blog.csdn.net/freak_csh/article/details/95585148
WebView启动优化？https://tech.meituan.com/2017/06/09/webviewperf.html



## WebView怎么加载assets中的html文件？
```
webView.loadUrl("file:///android_asset/test.html");
```



## WebView怎么和JS交互？
### WebView调用JS
- loadUrl()
```
<html>
<head>
    <meta http-equiv="Content-Type" charset="UTF-8"/>
    <script type="text/javascript">
        function androidCallJS(){
            alert("WebView调用JS");
        }
    </script>
</head>
</html>
```

```
webView.loadUrl("javascript:webViewCallJs()");
```
- evaluateJavascript()

### JS调用WebView




## 什么是WebView内核？
- 原生WebView内核
Android4.4以下内核是WebKit，4.4以上包括4.4是chromium
- 腾讯X5WebView内核
Blink内核(基于chromium)
## 如何查看WebView内核版本？
```
webView.getSettings().getUserAgentString();
```

```
Mozilla/5.0 (Linux; Android 7.1.1; RealtekATV Build/NMF26Q; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Safari/537.36
```


## 系统WebView
- 系统WebView所在路径/system/app/webview/
```
package:/system/app/webview/webview.apk=com.android.webview
```
- 系统WebView包名
```
67.0.3396.87版本包名是com.android.webview
81.0.4044.138版本包名是com.google.android.webview
```
- 系统WebView各版本下载地址
https://android-system-webview.cn.uptodown.com/android/versions
