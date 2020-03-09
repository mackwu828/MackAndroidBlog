

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