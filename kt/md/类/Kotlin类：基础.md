

## Java类的语法
```
public class Person{

}
```

## Kotlin类的语法
```
class Person
```
- Kotlin和Java一样都是用 `class` 关键字声明类。
- Kotlin的类默认是 `public final` 的。
- Kotlin的类体如果是空的，大括号可以省略。


## Java类的构造函数
```
public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }
}
```

## Kotlin类的构造函数
```
class Person (val name: String)
```

- Kotlin的构造函数可以直接写在类名后。
- Kotlin的构造函数实际使用 `constructor` 关键字声明，可以省略。声明私有构造函数时不能省略。
```
class Person private constructor(val name: String)
```
- Kotlin和Java一样，如果没有显示声明任何构造函数，默认会生成一个无参的构造函数。
- Kotlin的构造函数分为主构造函数和次构造函数。在类名后面声明的构造函数叫做主构造函数，在类中声明的构造函数叫做次构造函数。
```
class Person(val name: String) {
    constructor(name: String, age: Int) : this(name)
}
```


## Java类的初始化操作
```
public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
        System.out.println("name: " + name);
    }

}
```

## Kotlin类的初始化操作
```
class Person(val name: String) {

    init {
        println("name: " + name)
    }

}
```
- Kotlin类的构造函数不能有初始化操作。初始化操作可以放在 `init` 初始化块中。



## Kotlin的单例类
```
object Person
```
Kotlin用关键字 `object` 声明类，表示单例。相当于Java的饿汉式的单例类。


## Kotlin数据类
```
data class Person(val name: String)
```
Kotlin的数据类自动提供了类属性的访问器，`toString()`