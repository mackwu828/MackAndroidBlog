


so文件的加载流程？https://www.cnblogs.com/dasusu/p/9810673.html
System.loadLibrary里面做了什么？
classLoader?
android:extractNativeLibs属性

问题：
Looked for: [armeabi-v7a, armeabi], but only found: []？





## so文件的加载过程？
classLoader不为空：
1. 先通过classLoader#findLibrary寻找so
2. 如果没找到，会通过System#mapLibraryName再寻找so
3. 找到后调用nativeLoad方法加载so，否则报UnsatisfiedLinkError

classLoader为空：
什么时候classLoader会为空？

System#loadLibrary()
Runtime#loadLibrary0()
```
    private synchronized void loadLibrary0(ClassLoader loader, Class<?> callerClass, String libname) {
        String libraryName = libname;
        if (loader != null && !(loader instanceof BootClassLoader)) {
            String filename = loader.findLibrary(libraryName);
            if (filename == null &&
                    (loader.getClass() == PathClassLoader.class ||
                     loader.getClass() == DelegateLastClassLoader.class)) {
                filename = System.mapLibraryName(libraryName);
            }
            if (filename == null) {
                throw new UnsatisfiedLinkError(loader + " couldn't find \"" +
                                               System.mapLibraryName(libraryName) + "\"");
            }
            String error = nativeLoad(filename, loader);
            if (error != null) {
                throw new UnsatisfiedLinkError(error);
            }
            return;
        }
        getLibPaths();
        String filename = System.mapLibraryName(libraryName);
        String error = nativeLoad(filename, loader, callerClass);
        if (error != null) {
            throw new UnsatisfiedLinkError(error);
        }
    }
```



## data/system/packages.xml下可以查看自身so的加载路径

## apk安装后so没有被解压出来
- 在data/app/包名/lib/arm下没有so，而base.apk包含了so。
- 在application标签下加了extractNativeLibs为true之后，lib/arm下so被解压出来，base.apk的大小减小了so的大小。
```
    <application
        ...
        android:extractNativeLibs="true">
```