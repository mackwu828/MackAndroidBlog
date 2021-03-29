
https://blog.csdn.net/guolin_blog/article/details/38461239

## 命令行
```
adb shell 获取超级用户权限。#符号表示是超级用户，$符号表示普通用户。输入su切换用户身份
su 切换用户身份
cd data/data 前往data/data目录
ls 查看所有目录
```
![](https://upload-images.jianshu.io/upload_images/7004853-0a2deed9fbe0ed65.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


```
cd com.android.providers.contacts 
ls 可以看到cache databases files shared_prefs这几个子目录
```
其中databases是用于存放数据库文件的，files是用于存放普通文本文件的，lib是用于存放so库的，shared_prefs则是用于存放shared文件的
![](https://upload-images.jianshu.io/upload_images/7004853-1d81e1976f294436.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


```
cd databases
ls db后缀的是数据库文件，journal的文件是日志文件不用管
```
![](https://upload-images.jianshu.io/upload_images/7004853-e8ab66e0b8aa7481.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## SQLiteOpenHelper