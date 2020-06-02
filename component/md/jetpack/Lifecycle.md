管理Activity和Fragment的生命周期

## 资料
- [Jetpack:Lifeccyle入门指南](https://juejin.im/post/5d0e47636fb9a07ee063207c)
- https://stackoverflow.com/questions/47656728/is-it-mandatory-to-remove-yourself-as-an-observer-from-android-lifecycle


## 如何使用
### 一、实现LifecycleObserver
`Lifecycle.Event` 对应Activity、Fragment的生命周期。如 `Lifecycle.Event.ON_CREATE` 对应 Activity 的 `onCreate`方法
```
class MyLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("TAG", "LifecycleObserver onCreate...")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        Log.d("TAG", "LifecycleObserver onStart...")
    }
}
```
### 二、实现LifecycleOwner
```
class TestLifecycleActivity : Activity(), LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)
    private val lifecycleObserver = MyLifecycleObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TAG", "onCreate...")
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        lifecycleRegistry.addObserver(lifecycleObserver)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

}
```
测试：
```
2020-01-06 14:58:54.413 5884-5884/com.mackwu.http D/TAG: onCreate...
2020-01-06 14:58:54.414 5884-5884/com.mackwu.http D/TAG: LifecycleObserver onCreate...
2020-01-06 14:58:54.417 5884-5884/com.mackwu.http D/TAG: onStart...
2020-01-06 14:58:54.417 5884-5884/com.mackwu.http D/TAG: LifecycleObserver onStart...
2020-01-06 14:58:54.420 5884-5884/com.mackwu.http D/TAG: onResume...
2020-01-06 14:58:54.420 5884-5884/com.mackwu.http D/TAG: LifecycleObserver onResume...

2020-01-06 14:59:03.140 5884-5884/com.mackwu.http D/TAG: LifecycleObserver onPause...
2020-01-06 14:59:03.140 5884-5884/com.mackwu.http D/TAG: onPause...
2020-01-06 14:59:03.538 5884-5884/com.mackwu.http D/TAG: LifecycleObserver onStop...
2020-01-06 14:59:03.538 5884-5884/com.mackwu.http D/TAG: onStop...
2020-01-06 14:59:03.538 5884-5884/com.mackwu.http D/TAG: LifecycleObserver onDestroy...
2020-01-06 14:59:03.539 5884-5884/com.mackwu.http D/TAG: onDestroy...
```

### 三、使用定义好的LifecycleOwner
在Support Library 26.1.0和更高版本，Activity和Fragment已经默认实现了LifecycleOwner。
```
class TestLifecycleActivity : AppCompatActivity(){

    private val lifecycleObserver = MyLifecycleObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(lifecycleObserver)
    }

}
```


##

观察者接口：LifecycleObserver
观察者实现：自定义类实现LifecycleObserver，通过注解@OnLifecycleEvent(Lifecycle.Event.XXX)方法，在生命周期发生改变时被调用

被观察者接口：LifecycleOwners
被观察者实现：AppCompatActivity(ComponentActivity)实现了LifecycleOwners

1. 怎么添加和移除LifecycleObserver对象？
通过LifecycleRegistry。LifecycleRegistry持有了LifecycleOwners对象，即这时的Activity对象。

实例化LifecycleRegistry
```
val lifecycleRegistry = LifecycleRegistry(this)
```
添加lifecycleObserver对象
```
lifecycleRegistry.addObserver(lifecycleObserver)
```
移除lifecycleObserver对象
```
lifecycleRegistry.removeObserver(lifecycleObserver)
```


2. AppCompatActivity需要持有LifecycleObserver对象
3. 当生命周期改变时，如何通知LifecycleObserver对象？
通过LifecycleRegistry
```
lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.XXX)
```


## ProcessLifecycleOwner?
