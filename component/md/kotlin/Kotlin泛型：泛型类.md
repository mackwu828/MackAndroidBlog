
泛型即参数化类型。将类型由原来的具体的类型参数化。

## Java泛型类
```
public class GenericClass<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }
}
```


## Kotlin泛型类
```
class GenericClass<T>(val data: T)
```


## Java限定泛型类的类型
```
public class GenericClass<T extends GenericData>{
    ...
}
```

## Kotlin限定泛型类的类型
```
class GenericClass<T : GenericData>(val data: T)
```