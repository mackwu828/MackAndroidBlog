

## 资料
[Android 快速发布开源项目到jcenter](https://blog.csdn.net/lmj623565791/article/details/51148825)
[](https://blog.csdn.net/linglongxin24/article/details/53415932)

## 注册账号
[https://bintray.com/](https://bintray.com/)

## 创建一个Repository
![](https://upload-images.jianshu.io/upload_images/7004853-45ab87e03d006d15.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 创建一个新的package
![](https://upload-images.jianshu.io/upload_images/7004853-1c78a1dda324413c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 查看API Key
账户信息 => Edit Profile => API Key

## 
在项目的build.gradle中添加
```
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.0'
        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.7.3'
        classpath 'com.github.dcendents:android-maven-gradle-plugin:1.5'
    }
```

在gradle.properties中配置bintray的账户名、密码和apikey
```
bintray.user=xxx
bintray.apikey=xxx
bintray.gpg.password=xxx
```

在要上传的module的build.gradle中添加
```
apply plugin: 'com.android.library'
apply plugin: 'com.github.dcendents.android-maven'
apply plugin: 'com.jfrog.bintray'

//定义版本标识
version = "0.1.1"

```


## 问题 
### HTTP/1.1 401 Unauthorized [message:This resource requires authentication]
原因：user或者apikey填写有误

### javadoc: 错误 - 非法的程序包名称: ...xxx.kt
原因： Kotlin 文件不能用 javadoc工具来生成 Javadoc, 导致了在执行bintrayUpload任务不能正确build。
解决：在项目的build.gradle文件中添加
```
 classpath "org.jetbrains.dokka:dokka-gradle-plugin:0.9.18"
```