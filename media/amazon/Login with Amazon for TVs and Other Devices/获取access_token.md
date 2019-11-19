
## 请求
```
        String url = "https://api.amazon.com/auth/O2/token";
        FormBody.Builder builder = new FormBody.Builder()
            .add("grant_type", "device_code")
            .add("device_code", deviceCode)
            .add("user_code", userCode);
```
地址：https://api.amazon.com/auth/O2/token

参数：
* grant_type: 必须是device_code
* device_code: 在codepair接口中获取
* user_code: 在codepair接口中获取

## 响应
```
{
    "access_token": "2YomnFZEjfjklsadjkwpAA",
    "token_type": "bearer",
    "expires_in": 3600,
    "refresh_token": "nGzv3JORFQXG3x21KW1a"
}
```

参数：
* access_token: 