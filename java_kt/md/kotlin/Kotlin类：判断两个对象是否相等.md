由于每次实例化一个对象时，系统会分配一块内存地址给这个对象，而系统默认是根据内存地址来检测是否是同一个对象，
所以就算是同一个类里实例化出来的对象它们也不会相等。

## 根据hashCode判断两个对象是否相等
```
public class JavaTest {

    public static void main(String[] args) {
        System.out.println(new JavaTest().toString());
        System.out.println(new JavaTest().toString());
    }
}
```

```
com.mackwu.kt.Person@12a3a380
com.mackwu.kt.Person@29453f44
```

```
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
```


## hashCode是否是内存地址？