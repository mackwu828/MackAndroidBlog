
## Java创建对象
```
        Person person = new Person();
```

## Kotlin创建对象
```
        val person = Person()
```

- Kotlin创建对象无需 `new`


## Java的匿名对象
```
public abstract class Person {

    public static void main(String[] args) {
        Person person = new Person(){
            
        };
    }
}
```

## Kotlin的匿名对象
```
abstract class Person

fun main() {
    val person = object : Person(){

    }
}
```

## Kotlin的伴生对象
```
class Person{
    
    companion object{
        private const val TAG = "TAG"
    }
}
```
Kotlin没有 `static` 关键字，无法声明静态成员，用 `companion object` 关键字声明伴生对象，内部的成员相当于Java的静态成员。
