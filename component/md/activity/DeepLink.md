
DeepLink
```

/**
 * 通过DeepLink启动另一个进程的Activity
 */
fun Activity.startDeepLink(packageName: String, activityName: String, uriString: String) {
        val intent = Intent()
                .apply { action = Intent.ACTION_MAIN }
                .apply { addCategory(Intent.CATEGORY_LAUNCHER) }
                .apply { component = ComponentName(packageName, activityName) }
                .apply { data = Uri.parse(uriString) } //"https://www.mackwu.com/test?query=xxx"
        startActivity(intent)
}
```

```
        <activity
            android:name=".DeepLinkActivity"
            android:exported="true">
            <intent-filter>
                <data android:scheme="http" />
                <data android:scheme="https" />
                <data android:host="www.mackwu.com" />
                <data android:path="/test" />

                <action android:name="android.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
            </intent-filter>
        </activity>
```