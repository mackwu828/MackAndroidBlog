
# 如何上传library到jcenter
jcenter：标准的Android library文件服务器

## 注册账号
官网地址：[https://bintray.com/](https://bintray.com/)


## 创建一个Repository

![](https://upload-images.jianshu.io/upload_images/7004853-45ab87e03d006d15.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
添加一个Package
![](https://upload-images.jianshu.io/upload_images/7004853-1c78a1dda324413c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

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