

## 通过包名和Activity名称启动
```
fun Activity.startPackage(packageName: String, activityName: String) = try {
    startActivity(Intent().apply { component = ComponentName(packageName, activityName) })
} catch (e: Exception) {
    e.printStackTrace()
}
```
异常1：Activity的exported属性必须是true，表示允许被其他进程调用。否则会报错
```             
java.lang.SecurityException: Permission Denial: starting Intent ... not exported from uid 10066
```
```
    <activity android:name=".TargetActivity"
       android:exported="true"/>
```
异常2：包名或者Activity不存在时，会报错。
```
android.content.ActivityNotFoundException: Unable to find explicit activity class ... have you declared this activity in your AndroidManifest.xml?
```


## 通过action启动
```
fun Activity.startAction(action: String) = try {
    startActivity(Intent(action))
} catch (e: Exception) {
    e.printStackTrace()
}
```
异常：找不到action时，会报错。没有添加 `android.intent.category.DEFAULT` 也会报错。exported属性不需要时true
```
android.content.ActivityNotFoundException: No Activity found to handle Intent { act=com.mackwu.action.TARGET }
```
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