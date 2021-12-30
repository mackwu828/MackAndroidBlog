

什么是LiveData？
- LiveData是一个可以被观察的数据持有类。LiveData的数据发生改变时，会自动通知所有观察者。
- LiveData可以感知Activity、Fragment、Service等组件的生命周期。

LiveData注册后是否需要解除注册？不需要。  
LiveData 只会通知处于 Active 状态的观察者，如果一个观察者处于 Paused 或 Destroyed 状态，它将不会受到通知。
所以 LiveData 不需要在 Activity/Fragment 的 onPause 或 onDestroy 中接触订阅。
一旦观察者重新恢复 Resumed 状态，它将会重新收到 LiveData 的最新数据。

LiveData如何实现事件总线？https://tech.meituan.com/2018/07/26/android-livedatabus.html



##



```
    private val liveData = object : MutableLiveData<String>() {
        override fun onActive() {
            super.onActive()
            Log.d("TAG", "onActive...")
        }

        override fun onInactive() {
            super.onInactive()
            Log.d("TAG", "onInactive...")
        }
    }
//        // MutableLiveData
//        val userLiveData = MutableLiveData<User>()
//        // Observer
//        val userObserver = Observer<User> { user -> btn_test.text = user.name }
//        // observe
//        userLiveData.observe(this, userObserver)
//
//        // Transformations#map()
//        val userNameLiveData = Transformations.map(userLiveData) { user -> user.name }
//        val userNameObserver = Observer<String> { userName -> btn_test.text = userName }
//        userNameLiveData.observe(this, userNameObserver)
//
//        // MediatorLiveData
//        val mediatorLiveData = MediatorLiveData<User>()
//        val changeObserver = Observer<User> { value -> btn_test.text = value.name }
//        mediatorLiveData.addSource(userLiveData){ userLiveData.value = User("", "a", "")}
//        mediatorLiveData.addSource(userLiveData){ userLiveData.value = User("", "b", "")}
//        mediatorLiveData.observe(this, changeObserver)
//
//        // Transformations#switchMap()
//        Transformations.switchMap(userLiveData) { user -> MutableLiveData<String>() }
```

MutableLiveData
```
        val userLiveData = MutableLiveData<User>()
        // Observer
        val userObserver = Observer<User> { user -> btn_test.text = user.name }
        // observe
        userLiveData.observe(this, userObserver)
        // 改变数据
        btn_test.setOnClickListener { userLiveData.value = User("", "xxx", "") }
```

Transformations#map()
```
        val userNameLiveData = Transformations.map(userLiveData) { user -> user.name }
        val userNameObserver = Observer<String> { userName -> btn_test.text = userName }
        userNameLiveData.observe(this, userNameObserver)
```

