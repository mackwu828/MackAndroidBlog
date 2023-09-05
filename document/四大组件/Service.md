
Service的启动方式？启动服务、绑定服务
Service的类型？前台服务、后台服务、粘性服务
Service的生命周期
Service的 onStartCommand 的返回值？
Service有哪些保活方式？

IntentService
无障碍服务是什么？AccessibilityService
DreamService是什么？白日梦、屏保



AIDL
Message


## Service的启动方式
### 启动服务


### 绑定服务
```
    @Nullable
    @Override
    public IBinder onBind(@NonNull Intent intent) {
        super.onBind(intent);
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
```

```
public class MyServiceConnection implements ServiceConnection {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MyService.MyBinder binder = (MyService.MyBinder) service;
        MyService myService = binder.getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
```

```
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, new MyServiceConnection(), Service.BIND_AUTO_CREATE);
```


## Service有哪些保活方式？
- 提高Service的优先级？
为防止Service被系统回收，可以通过提高优先级解决。1000是最高优先级，数字越小，优先级越低
```
        <service android:name=".service.TestService">
            <intent-filter android:priority="1000"/>
        </service>
```

- 设置为系统服务？
将进程设置为系统进程，那么服务也就变成系统服务。添加 `android:persistent="true"` 到 application 中
```
    <application
        android:name=".ComponentApp"
        android:persistent="true"
        ...>
```

- 设置为前台服务
- 利用ANDROID的系统广播检查Service的运行状态，如果被杀掉，就再起来？
利用的系统广播是Intent.ACTION_TIME_TICK，这个广播每分钟发送一次，我们可以每分钟检查一次Service的运行状态，如果已经被结束了，就重新启动Service。