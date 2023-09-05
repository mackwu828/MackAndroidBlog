
## Java类的属性
```
public class Person {

    private String name;

}
```

## Kotlin类的属性
```
class Person{

    private val name: String = "xxx"

}
```
- Kotlin类的属性需要在声明的时候初始化。避免声明的时候初始化的方式有两种：延迟初始化或者声明为可空类型


### Kotlin类的属性声明为可空类型
```
class Person {

    private var name: String? = null

}
```


### Kotlin类的属性的延迟初始化方式一：`lateinit var`
```
class Person{

    private lateinit var name: String

}
```
判断 `lateinit var` 属性是否已经初始化
```
class Person{

    private lateinit var name: String

    fun isNameInit(){
        if (::name.isInitialized) println("name has initialized") else println("name has not initialized")
    }
}
```

### Kotlin类的属性的延迟初始化方式一：`by lazy`
```
class Person {

    private val name by lazy { "xxx" }

}
```
`by lazy` 初始化的代码是在对象第一次被调用的时候才会执行，后续调用不会再执行，会返回记录的结果。


## Java类属性的访问器
```
public class Person {

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    private String name;

}
```

## Kotlin类属性的访问器
```
    private var name: String
        get() = name
        set(value) {
            name = value
        }
```
Kotlin默认提供了类属性的访问器， getter和setter可以省略。