
# Maven
Maven是Apache开发的一个工具，提供了用于贡献library的文件服务器。总的来说，只有两个标准的Android library文件服务器：jcenter和mavenCentral

## 资料


## 模块的build.gradle
如果你想在Android Studio中引入一个library，只要在模块的build.gradle中添加依赖
```
dependencies {
    implementation 'com.android.support:multidex:1.0.3'
}
```
在添加了上面的依赖之后，gradle会访问Maven仓库服务器该library是否存在。如果存在，gradle会获取该library的路径，
将library下载到本地，然后和项目一起编译。


## 项目的build.gradle
Android Studio会从项目的build.gradle定义的Maven文件服务器下载library。如默认添加的jcenter()文件服务器
```
allprojects {
    repositories {
        jcenter()
    }
}
```


## jcenter
jcenter是一由bintray.com维护的Maven仓库
```
allprojects {
    repositories {
        jcenter()
    }
}
```
## mavenCentral
mavenCentral是由sonatype.org维护的Maven仓库
```
allprojects {
    repositories {
        mavenCentral()
    }
}
```

## 自定义文件服务器
除了两个标准的服务器之外，如果我们使用的library的作者是把该library放在自己的服务器上，我们还可以自己定义特有的Maven仓库服务器
```
allprojects {
    repositories {
        // 自己的服务器
        maven { url "http://172.xxx:8081/repository/android_plantform/" }
        // jitpack服务器
        maven { url "https://jitpack.io" }
        // 阿里云的服务器
        maven { url 'http://maven.aliyun.com/nexus/content/groups/public' }
    }
}
```
起初，Android Studio选择mavenCentral作为默认仓库。如果你使用老版本的Android Studio创建一个新项目，mavenCentral()会自动的定义在build.gradle中。
但是mavenCentral的最大问题是对开发者不够友好，上传library异常困难。同时还因为诸如安全方面的其他原因，Android Studio团队决定把默认的仓库替换成jcenter。
正如你看到的，一旦使用最新版本的Android Studio创建一个项目，jcenter()自动被定义，而不是mavenCentral()。

