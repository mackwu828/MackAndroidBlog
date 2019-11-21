Activity是用户界面，负责和用户交互

## 生命周期
onCreate、



## 启动方式
### startActivity
```
startActivity(Intent(this, TargetActivity::class.java))
```
### startActivityForResult
重写onActivityResult，根据requestCode和resultCode做判断
```
    startActivityForResult(Intent(this, TargetActivity::class.java), 0x01)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 0x01) {
            
        }
    }
```
目标activity设置resultCode，默认是Activity.RESULT_OK。如果不需要根据resultCode判断可以省略
```
setResult(Activity.RESULT_OK)
setResult(0x02)
```
### 启动Activity的最佳方式
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

        
## 启动模式
Activity有四种启动模式：standard、singleTop、singleTask、singleInstance  
### 设置启动模式
在AndroidManifest的activity标签中设置启动模式。默认是standard
```
        <activity android:name=".activity.LaunchModeActivity"
            android:launchMode="standard"/>
```

### Activity栈 
Activity是通过Activity栈管理的，当一个Activity启动时，系统会根据配置将Activity添加到栈中。  
如果用户点击返回或者finish结束了该Activity，那么该Activity会从栈中移除

### adb命令查看Activity栈中的情况
```
打开控制台，输入
C:\Android\workspace\mackblog>adb shell
root@aosp:/ # dumpsys activity | grep -i run
```

### standard
Activity的默认启动方式。每次启动Activity都会新建一个Activity实例，然后添加到栈中。
```
 点击1次startActivity，输入adb命令，可以看到Activity栈中添加了1个Activity实例
       Running activities (most recent first):
       Run #0: ActivityRecord{71a16ba u0 com.mackwu.activity/.LaunchModeActivity t39}
 继续点击4次startActivity，输入adb命令，可以看到Activity栈中新添加了4个Activity实例
       Running activities (most recent first):
       Run #4: ActivityRecord{10bbac1f u0 com.mackwu.activity/.LaunchModeActivity t39}
       Run #3: ActivityRecord{9569441 u0 com.mackwu.activity/.LaunchModeActivity t39}
       Run #2: ActivityRecord{102c22d3 u0 com.mackwu.activity/.LaunchModeActivity t39}
       Run #1: ActivityRecord{1be20855 u0 com.mackwu.activity/.LaunchModeActivity t39}
       Run #0: ActivityRecord{71a16ba u0 com.mackwu.activity/.LaunchModeActivity t39}
```
### singleTop
### singleTask
### singleInstance



        
## 启动另一个进程的Activity
要启动的Activity的exported属性必须是true，表示允许其他进程调用。如果是false，会报错
```
Permission Denial: starting Intent ... not exported from uid 10066
```
### 通过包名和类名启动
```
        try {
            val intent = Intent().apply { component = ComponentName("com.mackwu.ipc", "com.mackwu.ipc.MainActivity") }
            startActivity(intent)
        } catch (e: Exception) {
            // 如果启动的包名或者类名不存在时，会报错。Unable to find explicit activity class {com.mackwu.ipc/com.mackwu.ipc.MainActivity}
            e.printStackTrace()
        }
```

### 通过action启动
```
        try {
            val intent = Intent("com.mackwu.ipc.action.TARGET")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
```

### 通过包名和getLaunchIntentForPackage启动
会启动另一个进程的主activity
```
        val intent = packageManager.getLaunchIntentForPackage("com.mackwu.ipc")
        intent?.run {
            startActivity(this)
        }
```

### DeepLink