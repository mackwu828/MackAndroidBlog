


Lifecycle有什么用？如何使用？ 用来绑定Activity和Fragment的生命周期
如何自定义LifecycleOwner？


Activity的onDestroy时是否需要移除LifecycleObserver？不需要。https://stackoverflow.com/questions/47656728/is-it-mandatory-to-remove-yourself-as-an-observer-from-android-lifecycle
Fragment中使用LifecycleRegister会出现Failed to call observer method？https://github.com/TakuSemba/RtmpPublisher/issues/14



## Lifecycle如何使用？
只要类实现了LifecycleOwner接口都可以直接添加LifecycleObserver。LifecycleOwner表示类具有生命周期。
如AppCompatActivity和Fragment中都可以直接添加LifecycleObserver。
```
    public static final String TAG = LifecycleActivity.class.getSimpleName();

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, "onCreate...");
        MyLifecycleObserver observer = new MyLifecycleObserver();
        getLifecycle().addObserver(observer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy...");
    }

    private static class MyLifecycleObserver implements LifecycleObserver {
        public static final String TAG = MyLifecycleObserver.class.getSimpleName();

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void onCreate(@NonNull LifecycleOwner owner){
            LogUtil.d(TAG, "onCreate...");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onDestroy(@NonNull LifecycleOwner owner){
            LogUtil.d(TAG, "onDestroy...");
        }
    }
```

```
2021-10-08 20:48:19.802 26131-26131/com.mackwu.component D/mack_wu: LifecycleActivity onCreate...
2021-10-08 20:48:19.805 26131-26131/com.mackwu.component D/mack_wu: MyLifecycleObserver onCreate...
2021-10-08 20:48:49.751 26131-26131/com.mackwu.component D/mack_wu: MyLifecycleObserver onDestroy...
2021-10-08 20:48:49.757 26131-26131/com.mackwu.component D/mack_wu: LifecycleActivity onDestroy...
```


## 如何自定义LifecycleOwner？
```
    private static class MyLifecycleOwner implements LifecycleOwner{
        private final LifecycleRegistry lifecycleRegistry;

        public MyLifecycleOwner() {
            lifecycleRegistry = new LifecycleRegistry(this);
            lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
            MyLifecycleObserver observer = new MyLifecycleObserver();
            getLifecycle().addObserver(observer);
        }

        public void onDestroy(){
            lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
        }

        @NonNull
        @Override
        public Lifecycle getLifecycle() {
            return lifecycleRegistry;
        }
    }
```














