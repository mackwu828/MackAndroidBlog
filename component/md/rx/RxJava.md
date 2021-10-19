
被观察者：Observable、Flowable、
观察者：Observer、Consumer

创建操作符。create、just、defer、empty()/never()/error()
转换操作符。map、flatMap、contactMap、buffer、debounce
过滤操作符。
组合操作符。
错误处理操作符。
Subject。既是被观察者也是观察者
RxJava导致内存泄漏？https://www.jianshu.com/p/65e8039ed755
RxJava的Consumer用lambda写法为什么没有内存泄漏？https://github.com/square/leakcanary/issues/1108




rxJava中文文档：
https://github.com/mcxiaoke/RxDocs
https://www.kancloud.cn/luponu/rxjava_zh/974450
操作符使用：https://juejin.cn/post/6844903617124630535#heading-68
实战例子：https://www.jianshu.com/p/c935d0860186
    token自动刷新：https://www.jianshu.com/p/6a452d93363c
    多个接口并发请求token如何只请求一次：https://www.jianshu.com/p/e88e61e1a721
Subject使用：https://www.jianshu.com/p/ecd70039f1a6


## 创建操作符


## 转换操作符

## Subject
Subject既是被观察者也是观察者。
Subject实例需要用Subject.create()创建，否则会被转成Observable。

- AsyncSubject
```
        // 不打印
//        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
//        asyncSubject.onNext(1);
//        asyncSubject.onNext(2);
//        asyncSubject.subscribe(System.out::println);
//        asyncSubject.onNext(3);
//        asyncSubject.onNext(4);

        // 只打印4
//        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
//        asyncSubject.onNext(1);
//        asyncSubject.onNext(2);
//        asyncSubject.subscribe(System.out::println);
//        asyncSubject.onNext(3);
//        asyncSubject.onNext(4);
//        asyncSubject.onComplete();

        // 只打印java.lang.NullPointerException
        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext(1);
        asyncSubject.onNext(2);
        asyncSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete...");
            }
        });
        asyncSubject.onError(new NullPointerException());
        asyncSubject.onNext(3);
        asyncSubject.onNext(4);
        asyncSubject.onComplete();
```

- BehaviorSubject

- PublishSubject
```
        // Observer会收到订阅后PublishSubject订阅后发送的所有数据。
        // 无打印
//        PublishSubject<Integer> publishSubject = PublishSubject.create();
//        publishSubject.onNext(1);
//        publishSubject.onNext(2);
//        publishSubject.subscribe(System.out::println);

        // 打印3、4
        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.subscribe(System.out::println);
        publishSubject.onNext(3);
        publishSubject.onNext(4);
```

- ReplaySubject


## RxJava导致内存泄漏？
RxJava在订阅后，当页面被finish，此时订阅逻辑还未完成，如果没有及时取消订阅，就会导致Activity/Fragment无法被回收，从而引起内存泄漏。
```
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println)
```

- Disposable
```
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        disposable = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribe(i -> LogUtil.d("onNext: " + i));
    }
                    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
```

- CompositeDisposable
```
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        compositeDisposable = new CompositeDisposable();
        Disposable disposable = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribe(i -> LogUtil.d("onNext: " + i));
        compositeDisposable.add(disposable);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
```

- RxLifecycle
https://github.com/trello/RxLifecycle

- AutoDispose
https://github.com/uber/AutoDispose





