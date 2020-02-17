
Activity是用户界面，负责和用户交互

## startActivity
```
val intent = Intent(this, TargetActivity::class.java)
startActivity(intent)
```

## startActivityForResult
```
 startActivityForResult(Intent(this, TargetActivity::class.java), 0x01)
```
### 重写onActivityResult，根据requestCode和resultCode做判断
```
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 0x01) {
            
        }
    }
```
### 目标activity设置resultCode，默认是Activity.RESULT_OK。如果不需要根据resultCode判断可以省略
```
setResult(Activity.RESULT_OK)
setResult(0x02)
```


## 启动Activity的最佳方式
在目标activity中定义一个静态方法，参数是context和要传入的参数
```
    companion object {
        fun start(context: Context, param: String){
            val intent = Intent(context, TargetActivity::class.java)
            intent.putExtra("param", param)
            context.startActivity(intent)
        }
    }
```


## 启动Activity时的生命周期
启动：
```
2020-01-20 16:49:21.068 5068-5068/com.mackwu.component D/LifecycleActivity: onCreate...
2020-01-20 16:49:21.076 5068-5068/com.mackwu.component D/LifecycleActivity: onStart...
2020-01-20 16:49:21.078 5068-5068/com.mackwu.component D/LifecycleActivity: onResume...
```

点击返回：Activity被销毁
```
2020-01-20 16:49:29.856 5068-5068/com.mackwu.component D/LifecycleActivity: onPause...
2020-01-20 16:49:30.244 5068-5068/com.mackwu.component D/LifecycleActivity: onStop...
2020-01-20 16:49:30.244 5068-5068/com.mackwu.component D/LifecycleActivity: onDestroy...
点击返回后启动：因为Activity已经被销毁了，所以会重新走onCreate
2020-01-21 16:39:30.723 2090-2090/com.mackwu.component D/LifecycleActivity: onCreate...
2020-01-21 16:39:30.730 2090-2090/com.mackwu.component D/LifecycleActivity: onStart...
2020-01-21 16:39:30.732 2090-2090/com.mackwu.component D/LifecycleActivity: onResume...
```

点击Home：Activity没有被销毁
```
2020-01-21 16:29:15.525 1804-1804/com.mackwu.component D/LifecycleActivity: onPause...
2020-01-21 16:29:15.570 1804-1804/com.mackwu.component D/LifecycleActivity: onStop...
点击Home后启动：因为Activity没有被销毁，所以会走onRestart，不会走onCreate
2020-01-21 16:29:30.437 1804-1804/com.mackwu.component D/LifecycleActivity: onRestart...
2020-01-21 16:29:30.438 1804-1804/com.mackwu.component D/LifecycleActivity: onStart...
2020-01-21 16:29:30.439 1804-1804/com.mackwu.component D/LifecycleActivity: onResume...
```

## 启动另一个Activity的生命周期
启动：源Activity会先调用 `onPause`, 等到另一个Activity调用了 `onResume` 后，源Activity再调用 `onStop`
```
2020-01-20 16:56:55.087 5263-5263/com.mackwu.component D/LifecycleActivity: onPause...
2020-01-20 16:56:55.105 5263-5263/com.mackwu.component D/TargetActivity: onCreate...
2020-01-20 16:56:55.110 5263-5263/com.mackwu.component D/TargetActivity: onStart...
2020-01-20 16:56:55.116 5263-5263/com.mackwu.component D/TargetActivity: onResume...
2020-01-20 16:56:55.560 5263-5263/com.mackwu.component D/LifecycleActivity: onStop...
```

点击返回：同上
```
2020-01-20 16:57:53.684 5263-5263/com.mackwu.component D/TargetActivity: onPause...
2020-01-20 16:57:53.686 5263-5263/com.mackwu.component D/LifecycleActivity: onRestart...
2020-01-20 16:57:53.688 5263-5263/com.mackwu.component D/LifecycleActivity: onStart...
2020-01-20 16:57:53.690 5263-5263/com.mackwu.component D/LifecycleActivity: onResume...
2020-01-20 16:57:54.114 5263-5263/com.mackwu.component D/TargetActivity: onStop...
2020-01-20 16:57:54.114 5263-5263/com.mackwu.component D/TargetActivity: onDestroy...
```


## 命令行启动Activity
```
adb shell
am start com.mackwu.component/.MainActivity
```