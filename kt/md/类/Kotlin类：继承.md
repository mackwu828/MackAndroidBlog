
## Java类的继承
```
public class Student extends Person {
}
```

## Kotlin类的继承
```
class Student : Person()
```

- Kotlin的继承用 `:`
- Kotlin的类默认是 `public final` 的，无法被继承，需要在类前添加 `open` 关键字。
```
open class Person
```
- Kotlin的类默认继承了 `Any` 类。类比Java的类默认继承了 `Object` 类。



## Java类的函数重写
```
public class Student extends Person {

    @Override
    protected void doSomething() {
        super.doSomething();
    }
}
```

## Kotlin类函数重写
```
class Student : Person(){

    override fun doSomething() {
        super.doSomething()
    }
}
```
Kotlin的函数也是默认 `public final` 的，无法被重写，需要将函数声明为 `open` 的。
```
open class Person {
    open fun doSomething() {

    }
}
```

## Kotlin类的属性重写
```
class Student : Person(){

    override var name: String = ""

}
```
Kotlin中允许属性的重载
