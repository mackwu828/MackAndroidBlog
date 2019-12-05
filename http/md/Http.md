
Http（Hypertext Transfer Protocol）是超文本传输协议，是 TCP/IP 协议簇中的应用层协议  
Http报文是Http在应用层交互数据的方式  
Http使用Uri传输数据和建立连接

Http1.1及之前的协议中，一个连接只能同时支持单个流？
Http2.0协议，可以支持多个流
SPDY?

## 资料

## Http请求报文
Http请求报文由请求行、请求头、空行和请求体四个部分组成
![](https://upload-images.jianshu.io/upload_images/7004853-ac24660f9da99904.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
- 请求行声明请求方法、请求路径、http 协议版本，如 `POST https://test/ http/1.1`
- 请求头声明客户端的部分信息，请求头的格式是 `name：value` ，如 `Content-Type: application/x-www-form-urlencoded`
- 请求体表示要上传给服务端的数据，请求体的格式是键值对`key=value&key2=value2` 或者 json ，如 `app_data_value=C67617447FEE4D82F9E22F6D171DAFA7CC...`


## Http响应报文
Http响应报文由状态行、响应头、空行和响应体四个部分组成
![](https://upload-images.jianshu.io/upload_images/7004853-17cd35512f82024e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 状态行声明 http 协议版本、状态码、状态码描述，如 ` 200 OK` 
- 响应头声明服务端的部分信息
- 响应体表示回传给客户端的数据



## Http1.1
- Http1.1 支持长连接，即在传输完数据后不断开 TCP 连接，继续在该通道上传输数据，减少了建立和关闭连接的消耗和延迟，在 Http1.1 中默认开启 `Connection： keep-alive`
- Http1.1 引入了更多的缓存策略
- Http1.1 支持断点续传
- Http1.1 增加了更多了错误响应码


## Https
Https协议需要CA申请证书，数据是使用SSL加密的，端口号是443。而Http不需要申请证书，数据是明文显示，端口号是80
