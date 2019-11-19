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

但是mavenCentral的最大问题是对开发者不够友好，上传library异常困难。
同时还因为诸如安全方面的其他原因，Android Studio团队决定把默认的仓库替换成jcenter。
正如你看到的，一旦使用最新版本的Android Studio创建一个项目，jcenter()自动被定义，而不是mavenCentral()。









