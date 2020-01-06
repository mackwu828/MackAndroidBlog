
## 绝对路径
绝对路径是相对于class文件来说的，所以得到的绝对路径就是项目所在的路径
```
val file = File("mack")
// 在main方法中运行。C:\Android\workspace\MackAndroidBlog\mack
// 在activity中运行。/mack
println(file.absolutePath) 
```

## 相对路径
```
val file = File("mack")
// 在main方法中运行。mack
// 在activity中运行。mack
println(file.path) 
```

## 外置存储

```
 Environment.getExternalStorageState(): mounted。用来判断sd卡是否被挂载。
 Environment.getExternalStorageDirectory(): /storage/emulated/0
 Environment.getExternalStorageDirectory(): /storage/emulated/0/Download
```

## 内置存储