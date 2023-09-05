
FileProvider、多窗口、3D Touch(7.1)

## 资料
[Android 7.0 行为变更 通过FileProvider在应用间共享文件吧](https://blog.csdn.net/lmj623565791/article/details/72859156)
[Android app 在线更新那点事儿（适配Android6.0、7.0、8.0）](https://juejin.im/post/5ad4ab7af265da239d49c8f9#heading-3)

## 通过FileProvider在应用间共享文件
Android7.0对文件的访问权限做了限制，不能使用 `file://` 格式的 URI 访问文件，否则会出现 FileUriExposedException 异常。
Android7.0需要使用 `content://` 格式的 URI 访问文件，并授予 URI 临时访问权限，授权的最简单方式是使用 FileProvider 类。
```
 android.os.FileUriExposedException: file:///storage/emulated/0/... exposed beyond app through Intent.getData()
```

1. 在 AndroidManifest 声明provider
```
        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="${applicationId}.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>
```

2. 新建xml文件夹，新建file_paths.xml文件
```
<?xml version="1.0" encoding="utf-8"?>
<paths>
    <!--  name名称，path该目录下的子目录 -->
    <!-- 设备的根目录-->
    <root-path
        name="root"
        path="/" />
    <!-- context.getFilesDir()-->
    <files-path
        name="files"
        path="/" />
    <!-- context.getCacheDir()-->
    <cache-path
        name="cache"
        path="/" />
    <!-- Environment.getExternalStorageDirectory()-->
    <external-path
        name="external"
        path="/" />
    <!-- context.getExternalFilesDirs() -->
    <external-files-path
        name="name"
        path="/" />
    <!-- context.getExternalCacheDirs()-->
    <external-cache-path
        name="name"
        path="/" />
</paths>
```

3. 通过FileProvider生成uri
```
    fun getFileUri(context: Context, file: File): Uri {
        val uri = if (Build.VERSION.SDK_INT >= 24) {
            FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
        } else {
            Uri.fromFile(file)
        }
        Log.d(TAG, "uri: $uri")
        return uri
    }
```

### 安装
```
                val intent = Intent(Intent.ACTION_VIEW)
                        .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
                        .apply { addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) }
                        .apply { setDataAndType(getFileUri(context, apkFile), "application/vnd.android.package-archive") }
                context.startActivity(intent)
```
如果没有添加`addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)`，会出现授权失败
```
Permission Denial: reading android.support.v4.content.FileProvider uri content://... from pid=21109, uid=10018 requires the provider be exported, or grantUriPermission()
```

### 拍照


### androidx
provider的name修改为
```
    android:name="androidx.core.content.FileProvider"
```