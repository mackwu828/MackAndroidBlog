

```
            try{
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.mackwu.demo");
                intent.setData(Uri.parse("http://www.mackwu.com/test?xxx=111"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }
```

```
        <activity
            android:name=".DeepLinkActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
            <intent-filter>
                <data android:scheme="http" />
                <data android:scheme="https" />
                <data android:host="www.mackwu.com" />
                <data android:path="/test" />
            </intent-filter>
        </activity>
```