

## 继承Service
```
open class LifecycleService: Service(){

    /**
     * 在服务第一次创建时调用
     */
    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "onCreate...")
    }

    /**
     * 在服务启动时调用。每次启动都会调用
     * @return START_STICKY 服务因内存不足被系统销毁后，会重新创建，然后调用onStartCommand，参数intent为空
     * START_NOT_STICKY 不会重新创建
     * START_REDELIVER_INTENT 会重新创建，并保存了之前的intent
     * START_STICKY_COMPATIBILITY START_STICKY的兼容版本
     *
     * super是返回START_STICKY或START_STICKY_COMPATIBILITY
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TAG", "onStartCommand...")
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 在服务被销毁时调用
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy...")
    }

    /**
     * 绑定服务时调用
     * @return 返回扩展的binder对象
     */
    override fun onBind(intent: Intent?): IBinder? {
        Log.d("TAG", "onBind...")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 解绑服务时调用
     */
    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("TAG", "onUnbind...")
        return super.onUnbind(intent)
    }
    
}
```

## onCreate

## onStartCommand

## onDestroy

## onBind

## onUnbind