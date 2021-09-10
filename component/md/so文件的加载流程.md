


so文件的加载流程？https://www.cnblogs.com/dasusu/p/9810673.html
System.loadLibrary里面做了什么？

## data/system/packages.xml下可以查看自身so的加载路径

## apk安装后so没有被解压出来
- 在data/app/包名/lib/arm下没有so，而base.apk包含了so。
- 在application标签下加了extractNativeLibs为true之后，lib/arm下so被解压出来，base.apk的大小减小了so的大小。
```
    <application
        ...
        android:extractNativeLibs="true">
```