

## 一、实现LifecycleObserver
`Lifecycle.Event.ON_CREATE` 对应 Activity 的 `onCreate`方法。其他同理。

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
2020-06-20 00:18:11.332 2526-2526/com.mackwu.component D/TAG: MyLifecycleObserver onCreate...
2020-06-20 00:18:11.333 2526-2526/com.mackwu.component D/TAG: MyLifecycleObserver onStart...
2020-06-20 00:18:11.335 2526-2526/com.mackwu.component D/TAG: MyLifecycleObserver onResume...
2020-06-20 00:18:25.956 2526-2526/com.mackwu.component D/TAG: MyLifecycleObserver onPause...
2020-06-20 00:18:26.359 2526-2526/com.mackwu.component D/TAG: MyLifecycleObserver onStop...
2020-06-20 00:18:26.360 2526-2526/com.mackwu.component D/TAG: MyLifecycleObserver onDestroy...
```
