
## Observable.create用法
```
    val observable = Observable.create(ObservableOnSubscribe<Int> { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
        emitter.onComplete()
    })
    val observer = object : Observer<Int> {
    
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe...")
        }

        override fun onNext(t: Int) {
            println("onNext...  t: $t")
        }

        override fun onError(e: Throwable) {
            println("onError...")
        }

        override fun onComplete() {
            println("onComplete...")
        }
    }
    // 订阅
    observable.subscribe(observer)
```

## Observable.create => ObservableCreate
`Observable.create`工厂方法生成 `ObservableCreate` 的实例
```
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        ObjectHelper.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new ObservableCreate<T>(source));
    }
```
`ObservableCreate` 具体实现 `subscribeActual`方法，回调了`ObservableOnSubscribe`实例的`subscribe`方法
```
    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        CreateEmitter<T> parent = new CreateEmitter<T>(observer);
        observer.onSubscribe(parent);

        try {
            source.subscribe(parent);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            parent.onError(ex);
        }
    }
```

## ObservableOnSubscribe
`ObservableOnSubscribe`的`subscribe`方法在 `subscribeActual` 方法中被调用，
```
public interface ObservableOnSubscribe<T> {

    void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception;
}
```

`ObservableEmitter` 持有了 `observer` 对象，所以Emitter发送数据时，会用通知observer发送对象

```

```











