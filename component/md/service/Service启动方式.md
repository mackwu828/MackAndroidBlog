
  
## 启动服务
### 一、创建Service

### 二、启动服务
```
    val intent = Intent(this, LifecycleService::class.java)
    startService(intent)
```

### 三、停止服务
```
    val intent = Intent(this, LifecycleService::class.java)
    stopService(intent)
```


## 通过action启动另一个进程的Service
```
    Intent intent = new Intent("XXX");
    startService(intent);
```


## 绑定服务
### 一、创建Service
```
public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
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
}
```

### 二、创建ServiceConnection
```
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            MyService myService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
```

### 三、绑定服务
```
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
```

### 四、解绑服务