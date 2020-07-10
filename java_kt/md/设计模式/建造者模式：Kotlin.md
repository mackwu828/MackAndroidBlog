
kotlin中有命名参数，不推荐使用建造者模式。如果非要使用建造者模式，写法如下

```
class Person(
        val name: String,
        val age: Int
) {

    constructor(builder: Builder) : this(builder.name, builder.age)

    class Builder {
        var name: String = ""
        var age: Int = 0

        fun name(name: String) = apply { this.name = name }

        fun age(age: Int) = apply { this.age = age }

        fun build(): Person {
            return Person(this)
        }
    }
}
```