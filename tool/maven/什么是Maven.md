
Maven是Apache开发的一个工具，提供了用于贡献library的文件服务器。总的来说，只有两个标准的Android library文件服务器：jcenter和mavenCentral


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

