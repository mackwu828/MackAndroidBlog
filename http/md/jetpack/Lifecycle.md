管理Activity和Fragment的生命周期

## 资料
[Jetpack:Lifeccyle入门指南](https://juejin.im/post/5d0e47636fb9a07ee063207c)



## 如何使用
### 一、LifecycleObserver

Lifecycle.Event对应Activity、Fragment的生命周期
```
class MyLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("TAG", "LifecycleObserver onCreate...")
    }

}
```
## 二、LifecycleOwner
在Activity中注册观察者。在Support Library 26.1.0和更高版本，Activity和Fragment已经默认实现了LifecycleOwner。
```
lifecycle.addObserver(myLifecycleObserver)
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


## 自定义LifecycleOwner

## ProcessLifecycleOwner?
