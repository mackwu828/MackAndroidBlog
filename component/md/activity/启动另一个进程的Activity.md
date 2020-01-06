

## 通过包名和类名启动。不推荐，推荐使用action

```
    try {
        val intent = Intent().apply { component = ComponentName(packageName, activityName) }
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    
    <activity android:name=".TargetActivity"
       android:exported="true"/>
```
启动的包名或者Activity不存在时，会报错。
```
android.content.ActivityNotFoundException: Unable to find explicit activity class ... have you declared this activity in your AndroidManifest.xml?
```
启动的Activity的exported属性必须是true，默认为false，表示允许其他进程调用。如果是false，会报错
```             
java.lang.SecurityException: Permission Denial: starting Intent ... not exported from uid 10066
```



## 通过action启动
```
        try {
            val intent = Intent(action)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
                <activity android:name=".TargetActivity"
                    android:exported="true">
                    <intent-filter>
                        <action android:name="com.mackwu.action.TARGET"/>
                        <category android:name="android.intent.category.DEFAULT" />
                    </intent-filter>
                </activity>
```
启动的Activity除了action，还需要添加category，否则会报错。
```
android.content.ActivityNotFoundException: No Activity found to handle Intent { act=com.mackwu.action.TARGET }
```



## 通过LaunchIntent
会启动另一个进程的主activity
```
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        intent?.run {
            startActivity(this)
        }
```