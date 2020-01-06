
DeepLink


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