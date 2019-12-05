## Login with Amazon
官网: https://developer.amazon.com/zh/apps-and-games/login-with-amazon  
LWA: Login with Amazon
CBL: code-based linking。cbl是一个鉴权的方式，设备通过显示userCode和url，让用户访问url，输入userCode，完成鉴权。


## 注册和登录


## 获取user_code和verification_uri
```
        String url = "https://api.amazon.com/auth/O2/create/codepair";
        FormBody.Builder builder = new FormBody.Builder()
            .add("response_type", "device_code")
            .add("client_id", "xxxxxxxxxxxxxx")
            .add("scope", "alexa:all")
            .add("scope_data", "xxxxx")
```

地址：https://api.amazon.com/auth/O2/create/codepair

参数：
- response_type: 必须是device_code
- client_id: 在安全配置文件中获取
- scope: alexa:all
- scope_data: json字符串，包含productID和deviceSerialNumber。如"{\"alexa:all\":{\"productID\":\"xxx\",\"productInstanceAttributes\":{\n\"deviceSerialNumber\":\"12345\"}}}"

或者用jsonObject拼接
```
JSONObject productInstanceAttributes = new JSONObject();
productInstanceAttributes.put("deviceSerialNumber", PRODUCT_DSN);

JSONObject scopeData = new JSONObject();
scopeData.put("productInstanceAttributes", productInstanceAttributes);
scopeData.put("productID", PRODUCT_ID);

... 
.add("alexa:all", scopeData)
```


## 响应
```
{
	"device_code": "d1e4836b-2799-4294-9379-0025b37ec06c",
	"expires_in": 600,
	"interval": 30,
	"user_code": "BEYHYS",
	"verification_uri": "https://amazon.com/us/code"
}
```