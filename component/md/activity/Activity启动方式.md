
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

## 命令行启动Activity
```
adb shell
am start com.mackwu.component/.MainActivity
```

## 通过包名和Activity名称启动
```
/**
 * 通过包名和Activity名称启动另一个进程的Activity
 * 异常1：java.lang.SecurityException: Permission Denial: starting Intent ... not exported from uid 10066
 * 异常2：android.content.ActivityNotFoundException: Unable to find explicit activity class ... have you declared this activity in your AndroidManifest.xml?
 */
fun Activity.startPackageActivity(packageName: String, activityName: String) = try {
    startActivity(Intent().apply { component = ComponentName(packageName, activityName) })
} catch (e: Exception) {
    e.printStackTrace()
}
```

异常1：Activity的exported属性是false会出现(默认是false) java.lang.SecurityException: Permission Denial: starting Intent ... not exported from uid 10066  
```
    <activity android:name=".TargetActivity"
       android:exported="true"/>
```

异常2：Activity不存在时会出现 android.content.ActivityNotFoundException: Unable to find explicit activity class ... have you declared this activity in your AndroidManifest.xml?  

注：关于exported属性，如果activity标签里有设置了intent-filter标签的话，exported属性默认是true

## 通过action启动
```
/**
 * 通过action启动另一个进程的Activity
 * 异常：android.content.ActivityNotFoundException: No Activity found to handle Intent { act=com.xxx }
 */
fun Activity.startActionActivity(action: String) = try {
    startActivity(Intent(action))
} catch (e: Exception) {
    e.printStackTrace()
}
```
异常：找不到action时或者没有添加 `android.intent.category.DEFAULT` 会出现android.content.ActivityNotFoundException: No Activity found to handle Intent { act=com.xxx }
```
                <activity android:name=".TargetActivity"
                    android:exported="true">
                    <intent-filter>
                        <action android:name="com.mackwu.action.TARGET"/>
                        <category android:name="android.intent.category.DEFAULT" />
                    </intent-filter>
                </activity>
```


## 通过LaunchIntent启动另一个进程的MainActivity(android.intent.action.MAIN)
```
fun Activity.startLaunch(packageName: String) = packageManager.getLaunchIntentForPackage(packageName)?.run { startActivity(this) }
```