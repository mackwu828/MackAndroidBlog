

## 被观察者接口
`ObservableSource` 接口中只提供了注册观察者的方法
```
public interface ObservableSource<T> {

    /**
     * 注册观察者
     */
    void subscribe(@NonNull Observer<? super T> observer);
}
```

`Observable` 实现了 `ObservableSource`，但是注册观察者的方法还是让子类去实现
```
public abstract class Observable<T> implements ObservableSource<T>{

    protected abstract void subscribeActual(Observer<? super T> observer);

    public final void subscribe(Observer<? super T> observer) {
        try {
            observer = RxJavaPlugins.onSubscribe(this, observer);
            subscribeActual(observer);
        } catch (Throwable e) {
        }
    }
}
```

## 观察者接口
```
public interface Observer<T> 
```


## 被观察者实现
### Observable.create => ObservableCreate
`Observable.create`工厂方法生成 `ObservableCreate` 的实例
```
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        ObjectHelper.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new ObservableCreate<T>(source));
    }
```
`ObservableCreate` 具体实现 `subscribeActual`方法
```
public final class ObservableCreate<T> extends Observable<T> {

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
}
```

### Observable.just => ObservableJust
### ...


## 观察者实现