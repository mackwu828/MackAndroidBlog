


## 一、实现LifecycleObserver
`Lifecycle.Event.ON_CREATE` 对应 Fragment 的 `onViewStateRestored`方法。  
`Lifecycle.Event.ON_DESTROY` 对应 Fragment 的 `onDestroyView`方法。  
其他同名。  

```
public class MyLifecycleObserver implements LifecycleObserver, ILifecycle {

    private static final String HEAD = MyLifecycleObserver.class.getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void onCreate() {
        Log.d("TAG", HEAD + " onCreate...");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    @Override
    public void onStart() {
        Log.d("TAG", HEAD + " onStart...");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onResume() {
        Log.d("TAG", HEAD + " onResume...");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    @Override
    public void onPause() {
        Log.d("TAG", HEAD + " onPause...");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    @Override
    public void onStop() {
        Log.d("TAG", HEAD + " onStop...");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void onDestroy() {
        Log.d("TAG", HEAD + " onDestroy...");
    }

}
```


## 二、在Activity中绑定LifecycleObserver
onCreate中添加，无需解除绑定。
```
        MyLifecycleObserver lifecycleObserver = new MyLifecycleObserver();
        getLifecycle().addObserver(lifecycleObserver);
```

```
2020-06-20 02:08:08.509 3696-3696/com.mackwu.component D/TAG: LifecycleFragment onViewStateRestored...
2020-06-20 02:08:08.509 3696-3696/com.mackwu.component D/TAG: MyLifecycleObserver onCreate...

2020-06-20 02:08:08.509 3696-3696/com.mackwu.component D/TAG: LifecycleFragment onStart...
2020-06-20 02:08:08.510 3696-3696/com.mackwu.component D/TAG: MyLifecycleObserver onStart...

2020-06-20 02:08:08.510 3696-3696/com.mackwu.component D/TAG: LifecycleFragment onResume...
2020-06-20 02:08:08.510 3696-3696/com.mackwu.component D/TAG: MyLifecycleObserver onResume...
```
